package com.learnlogins.back.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NaverDTO {

    private String id;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    private String age;

    private String birthday;

    @JsonProperty("profile_image")
    private String profileImage;

    private String birthyear;

    private String mobile;

}
