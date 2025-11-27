package com.study.Post;

import com.study.domain.post.PostRequest;
import com.study.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void saveBySearch() {
        int num = 1000;
        for (int i =0;i<num;i++) {
            PostRequest postRequest = new PostRequest();
            postRequest.setTitle( i +"번 게시글 제목 Test");
            postRequest.setContent(i +"게시글 내용 Test");
            postRequest.setWriter(i+"Tester");
            postRequest.setNoticeYn(false);
            Long id = postService.savePost(postRequest);
            System.out.println(id);

        }
    }
}
