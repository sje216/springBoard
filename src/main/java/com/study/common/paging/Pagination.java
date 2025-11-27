package com.study.common.paging;

import com.study.common.dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {

    private int totalDataCnt;     // 전체 데이터 수
    private int totalPageCnt;     // 전체 페이지 수
    private int startPage;        // 첫 페이지 번호
    private int endPage;          // 끝 페이지 번호
    private int offsetStart;      // offset 시작 위치
    private boolean existPrevPage;// 이전 페이지 존재 여부
    private boolean existNextPage;// 다음 페이지 존재 여부

    public Pagination(int totalDataCnt, SearchDto params) {
        if (totalDataCnt > 0) {
            this.totalDataCnt = totalDataCnt;
            calculation(params);
            params.setPagination(this);
        }
    }

    private void calculation(SearchDto params) {

        // 전체 페이지 수 계산
        totalPageCnt = ((totalDataCnt - 1) / params.getRecordSize()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호를 전체 페이지 수로 설정
        if (params.getPage() > totalPageCnt) {
            params.setPage(totalPageCnt);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지를 전체 페이지 수로 설정
        if (endPage > totalPageCnt) {
            endPage = totalPageCnt;
        }

        // offset 시작 위치 계산
        offsetStart = (params.getPage() - 1) * params.getRecordSize();

        // 이전 페이지 존재 여부
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부
        existNextPage = endPage < totalPageCnt;
    }
}
