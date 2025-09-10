package com.study.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Autowired // ApplicationContext spring container 중 하나 빈의 생성과 사용 관계 생명주기 등을 관리
    private ApplicationContext context;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() { // connection pool 라이브러리 중 하나
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() { // connection pool을 지원하기 위한 인터페이스 추가적으로 메서드 뿐 아니라 클래스 레벨도 선언
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlFacBean = new SqlSessionFactoryBean();
        sqlFacBean.setDataSource(dataSource());
        sqlFacBean.setMapperLocations(context.getResources("classpath*:/mappers/**/*Mapper.xml"));
        sqlFacBean.setConfiguration(mybatisConfig());
        return sqlFacBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception { // DB connection과 SQL 실행에 대한 모든것을 갖는 객체
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    // application.properties에 prefix로 시작하는 모든 설정을 읽어 빈 등록
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }
}
