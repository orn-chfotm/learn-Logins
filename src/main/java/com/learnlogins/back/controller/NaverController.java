package com.learnlogins.back.controller;

import com.learnlogins.back.data.dto.KakaoDTO;
import com.learnlogins.back.data.dto.NaverDTO;
import com.learnlogins.back.data.entity.MsgEntity;
import com.learnlogins.back.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("naver")
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception{
        NaverDTO naverInfo = naverService.getNaverInfo(request.getParameter("code"), request.getParameter("state"));

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", naverInfo));
    }

}
