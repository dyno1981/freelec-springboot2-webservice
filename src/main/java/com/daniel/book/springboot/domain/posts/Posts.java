package com.daniel.book.springboot.domain.posts;

import com.daniel.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // 기본 생성자 자동추가. public Posts() {}와 같은 효과
@Entity // 테이블과 링크될 클래스임을 나타냅니다. - ex) SalesManager.java -> sales_manager TABLE
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타냅니다. 스프링부트2.0에서는 GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됩니다.
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 컬럼을 나타냄, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다. 사용이유는 추가 옵션이 필요할때 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
