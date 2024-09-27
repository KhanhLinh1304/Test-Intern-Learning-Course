package com.example.learning_intern.component.jwt;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
AuthenticationEntryPoint được ExceptionTranslationFilter sử dụng để bắt đầu
một chương trình xác thực. Đây là điểm vào để kiểm tra xem người dùng đã được
xác thực hay chưa và đăng nhập người đó hoặc đưa ra ngoại lệ (không được phép).
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        System.out.println(authException.getMessage());

    }
}
