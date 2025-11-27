package com.study.domain.file;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileRequest {

    private Long id;                // 파일 번호 PK
    private Long postId;            // 게시글 번호 FK
    private String originalName;    // 원본 파일명
    private String saveName;        // 저장 파일명
    private Long fileSize;        // 파일 크기

    @Builder
    public FileRequest(String originalName, String saveName, Long fileSize) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.fileSize = fileSize;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
