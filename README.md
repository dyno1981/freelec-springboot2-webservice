# 스프링부트와 AWS로 혼자 구현하는 웹 서비스
[https://github.com/jojoldu/freelec-springboot2-webservice](https://github.com/jojoldu/freelec-springboot2-webservice)

저자 블로그 [https://jojoldu.tistory.com/463](https://jojoldu.tistory.com/463)   

### 처음같은 마음으로 소스마다 주석을, 그리고 이곳에 정리를 차곡히

2022.02.20 공부시작, 1장, 2장 완료

## 02장 스프링부트에서 테스트 코드를 작성하자

- 2-1 테스트 코드 소개

  TDD - 테스트 주도 개발 (Test Driven Development)

    - 레드 그린 사이클
        - 항상 실패하는 테스트를 먼저 작성 (Red)
        - 테스트가 통과하는 프로덕션 코드를 작성 ( Green)
        - 테스트가 통과하면 프로덕션 코드를 리팩토링 (Refactor)
    - 테스트코드 작성을 도와주는 프레임워크

  | JUnit | Java |
        | --- | --- |
  | DBUnit | DB |
  | OppUnit | C++ |
  | NUnit | .net |

  BDD - 행동 주도 개발 (Behaivor Driven Development)

    - TDD 근간으로 파생된 개발 프로세스
    - `비즈니스 요구사항에 집중하여` 테스트 케이스를 개발

  DDD - 도메인 주도 개발 (Domain Driven Development)

- 2-2 Hello Controller 테스트 코드 작성하기
    - 패키지 생성
        - 일반적으로 패키지명은 `웹사이트 주소의 역순`으로 한다.
        - @SpringBootApplication 어노테이션은 스프링부트 자동설정, 스프링Bean 읽기와 생성을 모두 자동으로 설정
          특히 @SpringBootApplication 이 있는 위치부터 설정을 읽어가기때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 함
        - SpringApplication.run 으로 내장 WAS를 실행 (내장 WAS사용을 권장 - ‘언제 어디서나 같은 환경에서 스프링부트 배포가능’)
        - Execution failed for task ':test'. 오류 해결
          - [File] > [Settings] > [Build, Execution, Deployment] > [Build Tools] > [Gradle]
          메뉴로 이동한 뒤, "Run tests using:" 옵션을 "IntelliJ IDEA" 를 선택

## 01장 인텔리제이로 스프링부트 시작하기

- 인텔리제이 프로젝트 생성하기
    1. New Project 클릭
    2. JDK 1.8 선택 or 다운로드, Next
    3. Location : 프로젝트 폴더 경로 선택
    4. 그룹명(패키지명), 아티펙트명(프로젝트명) 등록 후 Finish
    5. Gradle 빌드가 되면서 프로젝트 생성
    6. Gradle프로젝트 SpringBoot프로젝트로 변경하기

    ```groovy
    buildscript {
        ext {
            springBootVersion = '2.1.7.RELEASE'
        }
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath("org.springframework.book:spring-book-gradle-plugin:${springBootVersion}")
        }
    }
    
    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'org.springframework.book'
    apply plugin: 'io.spring.dependency-management'
    
    ...
    
    dependencies {
        compile('org.springframework.boot:spring-boot-starter-web')
    
        testCompile('org.springframework.boot:spring-boot-starter-test')
    }
    ```

    ```groovy
    buildscript {
        ext {
            springBootVersion = '2.1.7.RELEASE'
        }
        repositories {
            mavenCentral()
        }
        dependencies {
            classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        }
    }
    
    plugins {
        id 'java'
        id 'eclipse'
        id 'org.springframework.boot' version '2.1.7.RELEASE'
        id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    }
    
    group 'com.daniel.book'
    version '1.0-SNAPSHOT'
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-web'
    
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
    
        testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
    }
    
    test {
        useJUnitPlatform()
    }
    ```
