package com.learnlogins.back.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgEntity {

    private String id;
    private Object result;

    @Builder
    public MsgEntity(String id, Object result) {
        this.id = id;
        this.result = result;
    }
}
