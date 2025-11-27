package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.domain.file.FileRequest;
import com.study.domain.file.FileResponse;
import com.study.domain.file.FileService;
import com.study.domain.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor // 초기화 되지 않는 final 필드나 생성자 주입
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if(id != null) {
            PostRespose postRespose = postService.findById(id);
            model.addAttribute("post", postRespose);
        }
        return "post/write";
    }

    // 게시글 생성 페이지
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest postRequest, Model model) {
        Long id                 = postService.savePost(postRequest);
        List<FileRequest> files = fileUtils.uploadFiles(postRequest.getFiles());
        fileService.saveFile(id, files);
        MessageDto msg = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMsgAndRedirect(msg, model);
    }

    // 게시글 전체 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model){
        //List<PostRespose> postList = postService.findAll(params);
        //model.addAttribute("posts", postList);
        PagingResponse<PostRespose> res = postService.findAllPost(params);
        model.addAttribute("res", res);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(Model model, @RequestParam final Long id){
            PostRespose postRespose = postService.findById(id);
            model.addAttribute("post", postRespose);
        return "post/view";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest postRequest, Model model){
        // 게시글 정보 수정
        postService.updatePost(postRequest);

        // disk에 파일 업로드
        List<FileRequest> uploadfiles = fileUtils.uploadFiles(postRequest.getFiles());
        
        // database에 파일 정보 저장
        fileService.saveFile(postRequest.getId(), uploadfiles);

        // 삭제할 파일 정보 조회
        List<FileResponse> deleteFiles = fileService.getAllByIds(postRequest.getRemoveFileIds());

        // 디스크에 파일 삭제
        fileUtils.deleteFiles(deleteFiles);

        // database에 파일 삭제
        fileService.deleteAllByIds(postRequest.getRemoveFileIds());

        MessageDto msg = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMsgAndRedirect(msg, model);
    }

    // 기존 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(final Long id, final SearchDto searchParams, Model model){
        postService.deleteById(id);
        MessageDto msg = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(searchParams));
        return showMsgAndRedirect(msg, model);
    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMsgAndRedirect(final MessageDto messageDto, Model model) {
        model.addAttribute("msg", messageDto);
        return "common/msgRedirect";
    }

    //쿼리 스트링 파리미터를 Map에 담아 반환
    private Map<String, Object> queryParamsToMap(final SearchDto searchParams){
        Map<String, Object> data = new HashMap<>();
        data.put("page", searchParams.getPage());
        data.put("recordSize", searchParams.getRecordSize());
        data.put("pageSize", searchParams.getPageSize());
        data.put("keyword",  searchParams.getKeyword());
        data.put("searchType", searchParams.getSearchType());
        return data;
    }
}
