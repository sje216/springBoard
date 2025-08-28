package com.example.Config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.logging.Filter;

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 프로젝트에서 Bean을 정의하기 위한 클레스 지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootAppContext.class};
    }
    
    // Spring MVC 프로젝트 설정을 위한 클래스 지정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletAppContext.class};
    }

    // DispatcherServlet 에 매핑할 요청 주소 세팅
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    // 파라미터 인코딩 필터 설정
    protected Filter[] getSerFilters(){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[] {(Filter) encodingFilter};
    }
}
