package com.study.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.debug("============================================");
        log.debug("=================Begin======================");
        log.debug("Request URI =====> " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        log.debug("==================== END ===================");
        log.debug("===========================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
