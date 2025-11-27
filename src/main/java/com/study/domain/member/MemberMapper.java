package com.study.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /**
     * 회원정보 저장 (회원가입)
     * @param params - 회원정보
     */
    void save(MemberRequest memberRequest);

    /**
     * 회원 상세정보 조회
     * @param loginId - UK
     * @return 호ㅚ원 상세정보
     */
    MemberResponse findByLoginId(String loginId);

    /**
     * 회원 정보 수정
     * @param params - 회원정보
     */
    void update(MemberRequest memberRequest);

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param id - pk
     */
    void deleteById(Long id);

    /**
     * 회원 수 카운팅 (ID중복체크)
     * @params loginId - UK
     * @return 회원수
     */
    int cntByLoginId(String loginId);
}
