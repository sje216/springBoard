package com.study.domain.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 페이지
    @GetMapping("/login.do")
    public String openLogin(){
        return "member/login";
    }

    //회원 정보 저장 (회원가입)
    @PostMapping("/members")
    @ResponseBody
    public Long saveMember(@RequestBody final MemberRequest memberRequest){
        return memberService.saveMember(memberRequest);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findByLoginIdAndPassword(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }


    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@RequestBody final MemberRequest memberRequest, @PathVariable final Long id) {
        return memberService.updateMember(memberRequest);
    }


    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteByMemberById(final Long id) {
        return memberService.deleteMember(id);
    }

    //회원 수 ( ID 중복 체크 )
    @GetMapping("/member-cnt")
    public int cntMembers(@RequestParam final String loginId) {
        return memberService.cntByLoginId(loginId);
    }

    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberResponse Zlogin(HttpServletRequest request) {

        // 회원 정보  조회
        String loginId           = request.getParameter("loginId");
        String password          = request.getParameter("password");
        MemberResponse memberRes = memberService.login(loginId, password);

        // 세션에 회원 정보 저장, 세션 유지 시간 설정
        if(memberRes != null){
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", memberRes);
            session.setMaxInactiveInterval(60*30);
        }

        return memberRes;

    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // 세션 무효화
        session.invalidate();
        return "redirect:/login.do";
    }

}
