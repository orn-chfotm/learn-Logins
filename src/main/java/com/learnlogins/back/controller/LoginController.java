package com.learnlogins.back.controller;

import com.learnlogins.back.service.KakaoService;
import com.learnlogins.back.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;

    private final NaverService naverService;

    @RequestMapping("/loginPage")
    public String loginPage(Model model){

        model.addAttribute("appleUrl", "");
        model.addAttribute("kakaoUrl", kakaoService.getLoginUrl());
        model.addAttribute("naverUrl", naverService.getLoginUrl());

        return "login";
    }

}
