package com.daniel.book.springboot.web;

import com.daniel.book.springboot.config.auth.LoginUser;
import com.daniel.book.springboot.config.auth.dto.SessionUser;
import com.daniel.book.springboot.service.PostsService;
import com.daniel.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 기존에 (SessionUser) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선되었습니다.
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser를 저장하도록 구성했습니다.
        if (user != null) { // 세션에 값이 있을때만 model에 userName으로 등록합니다.
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
