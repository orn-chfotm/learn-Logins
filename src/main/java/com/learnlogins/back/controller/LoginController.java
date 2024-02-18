package com.learnlogins.back.controller;

import com.learnlogins.back.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;

    @RequestMapping("/loginPage")
    public String loginPage(Model model) {

        model.addAttribute("appleUrl", "");
        model.addAttribute("kakaoUrl", kakaoService.getLoginUrl());

        return "login";
    }

}
