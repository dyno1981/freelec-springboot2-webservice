package com.daniel.book.springboot.web.dto;

import org.junit.Test;

// JUnit의 assertThat은 CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않습니다.
// 자동완성이 좀 더 확실하게 지원됩니다.
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // assertj라는 테스트 검증 라이브러리의 검증 메소드, 체이닝이 지원되어 isEqualTo 와 같이 메소드를 이어서 사용 가능
        assertThat(dto.getAmount()).isEqualTo(amount); // isEqualTo는 assertj의 동등 비교 메소드
    }
}