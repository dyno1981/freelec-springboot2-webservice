package com.daniel.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링부트 자동설정, 스프링Bean 읽기와 생성을 모두 자동으로 설정
// 특히 @SpringBootApplication 이 있는 위치부터 설정을 읽어가기때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 함
@SpringBootApplication
//@EnableJpaAuditing // JPA Auditing 활성화
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

