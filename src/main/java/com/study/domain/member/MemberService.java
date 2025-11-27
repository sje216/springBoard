package com.study.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 정보 저장 (회원가입)
     * @param - 회원정보
     * @return PK
     */
    @Transactional
    public Long saveMember(final MemberRequest memberRequest) {
        memberRequest.encodingPw(passwordEncoder);
        memberMapper.save(memberRequest);
        return memberRequest.getId();
    }

    /**
     * 회원정보 상세조회
     * @params - loginId -UK
     * @return 회원 상세정보
     */
    public MemberResponse findMemberByLoginId(final String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    /**
     * 회원정보수정
     * @poram - 회원정보
     * @return PK
     */
    @Transactional
    public Long updateMember(final MemberRequest memberRequest) {
        memberRequest.encodingPw(passwordEncoder);
        memberMapper.update(memberRequest);
        return memberRequest.getId();
    }

    /**
     * 회원 정보 삭제
     * @param id - pk
     * @return PK
     */
    @Transactional
    public Long deleteMember(final Long id) {
        memberMapper.deleteById(id);
        return id;
    }

    /**
     * 회원 수 카우닝 (ID 중복 체크)
     * @param loginId - UK
     * @return 회원수
     */
    public int cntByLoginId(final String loginId) {
        return memberMapper.cntByLoginId(loginId);
    }

    /**
     * 로그인
     * @param loginId - 로그인 아이디
     * @param password - pw
     * @return 회원 상세정보
     */
    public MemberResponse login(final String loginId, final String password) {

        // 회원 정보 및 비번 조회
        MemberResponse memberResponse = memberMapper.findByLoginId(loginId);
        String encodedPassword        = (memberResponse == null) ? "" : memberResponse.getPassword();

        // 회원 정보 및 비번 체크
        if(memberResponse == null || passwordEncoder.matches(password, encodedPassword) == false) {
            return   null;
        }

        // 회원 응답 객체에서 비번 제거 후 회원정보 리턴
        memberResponse.clearPw();
        return memberResponse;
    }

}
