package com.example.learning_intern.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
