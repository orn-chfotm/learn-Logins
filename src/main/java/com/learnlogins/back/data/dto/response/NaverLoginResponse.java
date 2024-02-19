package com.learnlogins.back.data.dto.response;

import com.learnlogins.back.data.dto.NaverDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NaverLoginResponse {

    String resultcode;

    String message;

    NaverDTO response;

}
