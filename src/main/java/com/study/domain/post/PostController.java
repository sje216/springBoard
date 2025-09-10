package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor // 초기화 되지 않는 final 필드나 생성자 주입
public class PostController {

    private final PostService postService;

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if(id != null) {
            PostRespose postRespose = postService.findById(id);
            model.addAttribute("post", postRespose);
        }
        return "post/write";
    }

    @PostMapping("/post/save.do")
    public String savePost(final PostRequest postRequest){
        postService.savePost(postRequest);
        System.out.println(postRequest.getId());
        return "redirect:/post/list.do";
    }


}
