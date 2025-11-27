package com.study.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    // 파일 저장
    @Transactional
    public void saveFile(final Long postId, final List<FileRequest> files) {
        if(CollectionUtils.isEmpty(files)){
            return;
        }
        for(FileRequest file : files){
            file.setPostId(postId);
        }
        fileMapper.saveAll(files);
    }

    /**
     * 파일 리스트 조회
     * @param postId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    public List<FileResponse> getByPostId(final Long postId){
        return fileMapper.getByPostId(postId);
    }

    /**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일리스트
     */
    public List<FileResponse> getAllByIds(final List<Long> ids){
        if(CollectionUtils.isEmpty(ids)){
            return Collections.emptyList();
        }
        return fileMapper.getAllByIds(ids);
    }

    /**
     * 파일 삭제
     *
     * @param ids - PK list
     * @return 파일 리스트
     */
    @Transactional
    public void deleteAllByIds(final List<Long> ids){
        if(CollectionUtils.isEmpty(ids)){
            return;
        }
        fileMapper.deleteAllByIds(ids);
    }

    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보 조회
     */
    public FileResponse getById(final Long id){
        return fileMapper.getById(id);
    }

}
