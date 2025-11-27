package com.study.domain.comment;

import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    
    private final CommentService commentService;
    
    // 신규 댓글 생성 PathVariable RESTAPI 에서 리소스 표현하는데 사용 URI에서 템플릿 형태로 파라미터 전달 RequestBody 데이터 생성 또는 수정핤시 사용
    @PostMapping("/posts/{postId}/comments")
    public CommentResponse saveComment(@PathVariable final Long postId, @RequestBody final CommentRequest commentRequest) {
        Long id =  commentService.saveComment(commentRequest);
        return commentService.findById(id);
    }

    // 댓글 리스트 조회
    @GetMapping("/posts/{postId}/comments")
    public PagingResponse<CommentResponse> getComments(@PathVariable final Long postId, final CommentSearchDto commentSearchDto) {
        return commentService.findAllByPostId(commentSearchDto);
    }

    // 댓글 상세정보 조회
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentResponse getComment(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.findById(id);
    }

    // 기존 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final CommentRequest commentRequest) {
        commentService.updateComment(commentRequest);
        return commentService.findById(id);
    }

    // 기존 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.deleteById(id);
    }

}
