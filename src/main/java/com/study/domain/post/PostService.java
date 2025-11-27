package com.study.domain.post;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private  final  PostMapper postMapper;

    // 게시글 저장
    @Transactional
    public Long savePost(final PostRequest postRequest){
        postMapper.save(postRequest);
        return postRequest.getId();
    }

    // 게시글 상세정보 조회
    public PostRespose findById(final Long id){
        return postMapper.findById(id);
    }

    // 게시글 수정
    @Transactional
    public Long updatePost(final PostRequest postRequest){
        postMapper.update(postRequest);
        return postRequest.getId();
    }

    // 게시글 삭제
    public Long deleteById(final Long id){
        postMapper.deleteById(id);
        return id;
    }

    // 게시글 조회
    @Transactional
    public int cnt(final SearchDto searchDto){
        return postMapper.cnt(searchDto);
    }

     // 게시글 리스트 조회
    public List<PostRespose> findAll(final SearchDto params) {
        return postMapper.findAll(params);
    }

    // 게시글 리스트 조회
    public PagingResponse<PostRespose> findAllPost(final SearchDto params) {

        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int cnt = postMapper.cnt(params);
        if( cnt < 1){
            return new PagingResponse(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(cnt, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부 (offsetStart, recordSize) 를 기준으로 리스트 데이터 조회 후 응답 뎅리터 반환
        List<PostRespose> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

}
