package com.study.Post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.common.dto.SearchDto;
import com.study.domain.post.PostMapper;
import com.study.domain.post.PostRequest;
import com.study.domain.post.PostRespose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    //@Test
//    void save(){
//        PostRequest postRequest = new PostRequest();
//        postRequest.setTitle("게시글 제목 Test");
//        postRequest.setContent("게시글 내용 Test");
//        postRequest.setWriter("Tester");
//        postRequest.setNoticeYn(false);
//        postMapper.save(postRequest);
//
//        List<PostRespose> posts = postMapper.findAll(SearchDto);
//        int cnt = postMapper.cnt();
//        System.out.println(cnt);
//    }

    @Test
    void findById(){ // Jackson 라이브러리를 이용해 객체를 JSON문자열로 변환한 결과
        PostRespose postRespose = postMapper.findById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(postRespose);
            System.out.println(postJson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void update(){
        PostRequest postRequest = new PostRequest();
        postRequest.setId(1L);
        postRequest.setTitle("1번 게시글 제목 수정합니다.");
        postRequest.setContent("1번 게시글 내용 수정합니다.");
        postRequest.setWriter("도뎡이");
        postRequest.setNoticeYn(true);
        postMapper.update(postRequest);

        PostRespose postRespose = postMapper.findById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(postRespose);
            System.out.println(postJson);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Test
//    void delete(){
//        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//        postMapper.deleteById(1L);
//        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//    }
}
