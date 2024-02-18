package com.learnlogins.back.service;

import com.learnlogins.back.data.dto.KakaoDTO;

public interface KakaoService extends SnsLoginService{

    public KakaoDTO getKakaoInfo(String code) throws Exception;

    public KakaoDTO getUserInfoWithToken(String accessToken) throws Exception;
}
