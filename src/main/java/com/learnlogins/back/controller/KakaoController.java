package com.learnlogins.back.controller;

import com.learnlogins.back.data.dto.KakaoDTO;
import com.learnlogins.back.data.entity.MsgEntity;
import com.learnlogins.back.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        System.err.println(request.getParameter("code"));
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));

        System.err.println(kakaoInfo);

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
    }
}
