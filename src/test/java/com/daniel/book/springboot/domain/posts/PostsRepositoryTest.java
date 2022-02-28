package com.daniel.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit에서 단위 테스트가 끝날때마다 수행되는 메소드를 지정, 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 남아 테스트가 실패할 수 있습니다.
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void postsListSaveAndLoad() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 테이블 posts에 insert/update 쿼리를 실행합니다. id값이 있다면 update, 없다면 insert쿼리가 실행됩니다.
                .title(title)
                .content(content)
                .author("daniel@daniel.com")
                .build());
        // when
        List<Posts> postsList = postsRepository.findAll(); // posts 테이블에 있는 모든 데이터를 조회해오는 메소드
        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntitySave() {
        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        // when
        List<Posts> postsList = postsRepository.findAll();
        // then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>> greatedDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}