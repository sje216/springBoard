package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int cnt(){
        return postMapper.cnt();
    }

    // 게시글 리스트 조회
    public List<PostRespose> findAll(){
        return postMapper.findAll();
    }

}
