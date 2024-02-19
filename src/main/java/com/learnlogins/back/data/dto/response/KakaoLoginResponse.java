package com.learnlogins.back.data.dto.response;

import com.learnlogins.back.data.dto.KakaoDTO;
import com.learnlogins.back.data.dto.NaverDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoLoginResponse {

    String resultcode;

    String message;

    KakaoDTO response;

}
