package com.learnlogins.back.data.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ApiLoginResponse <T>{
    String resultcode;

    String message;

    T response;

    ApiLoginResponse(){}

    @Builder
    ApiLoginResponse (String resultcode, String message, T response) {
        this.resultcode = resultcode;
        this.message = message;
        this.response = response;
    }
}
