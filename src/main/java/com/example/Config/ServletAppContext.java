package com.example.Config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

// 설정 파일을 만들기 위한 Bean을 등록하기 위한 annotation
@Configuration
// Controller 어노테이션이 세팅되어 있는 클래스를 Controller 로 등록
@EnableWebMvc
// property 파일 지정
@PropertySource("classpath:properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {

    //@Value annotation으로 String 주입
    @Value("${db.classname}")
    private String dbName;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    //BasicDataSource 객체를 이용해 el태그로 불러온 각각의 문자열 저장 
    // --> 디비와 연동하는 정보 저장 ( 접속 정보 관리하는 객체 )
    @Bean
    public BasicDataSource  dataSource() {
        BasicDataSource  source = new BasicDataSource ();
        source.setDriverClassName(dbName);
        source.setUrl(dbUrl);
        source.setUsername(dbUsername);
        source.setPassword(dbPassword);

        return source;

    }
    
    // SqlSession 객체는 Connection을 생성하거나 원하는 SQL을 전달, 결과 리턴
    // 이 객체를 만들어내는 객체가 SqlSessionFactory
    //BasicDataSource에 담긴 DBCP연동 정보를 불러와 
    //SqlSessionFactoryBean에 저장하고 getObject() 메서드 사용 -> 반환된 정보 리턴
    @Bean
    public SqlSessionFactory sqlFac(BasicDataSource so) throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(so);
        SqlSessionFactory sf = sfb.getObject();

        return sf;
    }

    // Controller의 메서드가 반환하는 JSP 의 앞뒤로 경로와 확장자를 붙여주도록 설정
    public void configurViewResolver(ViewResolverRegistry registry){
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 정적 파일 (CSS,JS,IMG...)의 경로를 매핑
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
