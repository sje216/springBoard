package com.study.interceptor;

import com.study.domain.member.MemberResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginChkInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 세션에서 회원 정보 조회
        HttpSession session   = request.getSession();
        MemberResponse member =  (MemberResponse)session.getAttribute("loginMember");

        // 회원 정보 체크
        if(member == null || member.isDeleteYn() == true){
            response.sendRedirect("/login.do");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
