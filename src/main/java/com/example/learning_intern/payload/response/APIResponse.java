package com.example.learning_intern.payload.response;

import lombok.Data;

@Data
public class APIResponse {
    private Integer status;
    private Integer statusCode;
    private Object result;
    private String message;

    public APIResponse(Integer status, Integer statusCode, Object result, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.result = result;
        this.message = message;
    }
}
