package com.learnlogins.back.data.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {

    private long id;

    private String email;

    private String nickname;

    private String name;

    private String ageRange;

    private String birthday;

    private String birthyear;

    private String gender;

}
