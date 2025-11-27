package com.study.domain.file;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileResponse {

    private Long id;                    // 파일 아이디 PK
    private Long postId;                // 게시글 아이디 FK
    private String originalName;        // 파일 원래 이름
    private String saveName;            // 파일 저장할 이름
    private Long fileSize;              // 파일 사이즈
    private boolean deleteYn;           // 삭제 유무
    private LocalDateTime createdDate;  // 생성일자
    private LocalDateTime updatedDate;  // 수정일자

}
