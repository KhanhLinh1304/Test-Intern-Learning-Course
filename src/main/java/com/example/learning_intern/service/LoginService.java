package com.example.learning_intern.service;


import com.example.learning_intern.component.jwt.JwtTokenProvider;
import com.example.learning_intern.payload.request.LoginRequest;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.payload.response.TokenResponse;
import com.example.learning_intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public LoginService(JwtTokenProvider jwtTokenProvider,
                        AuthenticationManager authenticationManager,
                        UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseService loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            TokenResponse tokenResponse = new TokenResponse(token);
            return new ResponseService(200, tokenResponse);
        } catch (Exception ex) {
            return new ResponseService(401, "Invalid credential");
        }


    }
}
