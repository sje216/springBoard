package com.study.config;

import com.study.interceptor.LoggerInterceptor;
import com.study.interceptor.LoginChkInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**", "/js/**",  "/images/**");

        registry.addInterceptor(new LoginChkInterceptor()).addPathPatterns("/**/*.do").excludePathPatterns("/log*");
    }
}
