package com.learnlogins.back.service;

import com.learnlogins.back.data.dto.NaverDTO;

public interface NaverService extends SnsLoginService{

    public NaverDTO getNaverInfo(String code, String state) throws Exception;

    public NaverDTO getUserInfoWithToken(String accessToken) throws Exception;
}
