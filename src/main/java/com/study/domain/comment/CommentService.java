package com.study.domain.comment;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    /**
     * 댓글저장
     * @param params - 댓글 정보
     * @return sequense PK
     */
    @Transactional
    public Long saveComment(final CommentRequest commentRequest) {
        commentMapper.save(commentRequest);
        return commentRequest.getId();
    }

    /**
     * 댓글 상세정보 조회
     * @param id - pk
     * @return 댓글 상세정보
     */
    public CommentResponse findById(final Long id) {
        return commentMapper.findById(id);
    }

    /**
     * 댓글 수정
     * @param params - 댓글 정보
     * @return PK
     */
    @Transactional
    public Long updateComment(final CommentRequest commentRequest) {
        commentMapper.update(commentRequest);
        return commentRequest.getId();
    }

    /**
     * 댓글 삭제
     * @param id -pk
     * @return pk
     */
    @Transactional
    public Long deleteById(final Long id) {
        commentMapper.deleteById(id);
        return id;
    }

    /**
     * 댓글 리스트 조회
     * @param postId - 게시글 번호 PK
     * @return 특정 게시글에 등록된 댓글 리스트
     */
    public PagingResponse<CommentResponse> findAllByPostId(final CommentSearchDto commentSearchDto) {
        int cnt = commentMapper.cnt(commentSearchDto);
        if( cnt < 1){
            return new PagingResponse<>(Collections.emptyList(),null);
        }

        Pagination pagination       = new Pagination(cnt, commentSearchDto);
        List<CommentResponse> list  = commentMapper.findAll(commentSearchDto);

        return new PagingResponse<>(list, pagination);
    }

    /**
     *  댓글 수  카웉이
     * @param postId - 게시글 번호 PK
     * @return int 게시글의 댓글 수 카운팅
     */
    public int cnt(final CommentSearchDto commentSearchDto) {
        return commentMapper.cnt(commentSearchDto);
    }

}
