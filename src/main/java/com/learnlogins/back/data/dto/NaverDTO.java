package com.learnlogins.back.data.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NaverDTO {

    private String id;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    private String age;

    private String birthday;

    private String profileImage;

    private String birthyear;

    private String mobile;
}
