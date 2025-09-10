package com.study.domain.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRespose {

    private Long id;                     // PK
    private String title;                // 제목
    private String content;              // 내용
    private String writer;               // 작성자
    private int viewCnt;                 // 조회수
    private boolean noticeYn;            // 공지글 여부
    private boolean deleteYn;            // 삭제여부
    private LocalDateTime createdDate;   // 생성일시
    private LocalDateTime modifiedDate;  // 수정일시
}
