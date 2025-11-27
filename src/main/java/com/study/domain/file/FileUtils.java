package com.study.domain.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    //C:\Users\gram\Desktop\Board
    private final String uploadPath = Paths.get("C:", "Users", "gram", "Desktop", "Board").toString();

    /**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 리스트
     * @return DB에 저장할 파일 정보 리스트
     */
    public List<FileRequest> uploadFiles(final List<MultipartFile> files) {
        List<FileRequest> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            if(file.isEmpty()) {
                continue;
            }
            fileList.add(uploadFile(file));
        }
        return fileList;
    }

    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
    public FileRequest uploadFile(final MultipartFile file) {
        if(file.isEmpty()) {
            return null;
        }
        String saveName     = generateSaveFilename(file.getOriginalFilename());
        String today        = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath   = getUploadPath(today) + File.separator + saveName;
        File uploadFile     = new File(uploadPath);

        try {
            file.transferTo(uploadFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileRequest.builder()
                .originalName(file.getOriginalFilename())
                .saveName(saveName)
                .fileSize(file.getSize())
                .build();
    }


    /**
     * 저장 파일명 생성
     * @param filename 원본파일명
     * @return 디스크에 저장할 파일명
     */
    private String generateSaveFilename(final String filename) {
        String uuid      = UUID.randomUUID().toString().replace("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addpath) {
        return makeDirectories(uploadPath + File.separator + addpath);
    }
    
    /**
     * 업로드 폴더 생성
     * @param path - upload 경로
     * @return upload 경로
     */
    private String makeDirectories(final String path) {
        File file = new File(path);
        if (file.exists() == false) {
            file.mkdirs();
        }
        return file.getPath();
    }

    /**
     * 파일 삭제
     * @param files - 삭제할 파일 정보 리스트
     */
    public void deleteFiles(final List<FileResponse> fileList) {
        if(CollectionUtils.isEmpty(fileList)) {
            return;
        }
        for (FileResponse file : fileList) {
            String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
            deleteFile(uploadedDate, file.getSaveName());
        }
    }

    /**
     * 파일 삭제
     * @param addPath - 추가 경로
     * @param filename - 파일명
     */
    private void deleteFile(final String addPath, final String fileName) {
        String filePath = Paths.get(uploadPath, addPath, fileName).toString();
        deleteFile(filePath);
    }

    /**
     * 파일 삭제
     * @param filePath - 파일 경로
     */
    private void deleteFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 다운로드할 첨부파일 (리소스) 조회
     * @param file - 첨부파일 상세정보
     * @return 첨부파일 (리소스)
     */
    public Resource readFileAsResource(final FileResponse file) {
        String upladedDate  = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String fileName     = file.getSaveName();
        Path filePath       = Paths.get(uploadPath, upladedDate, fileName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() == false || resource.isFile() == false) {
                throw new RuntimeException("file not found : " + filePath.toString());
            }
            return resource;
        }
        catch (Exception e) {
            throw new RuntimeException("file not found : " + filePath.toString());
        }
    }

}
