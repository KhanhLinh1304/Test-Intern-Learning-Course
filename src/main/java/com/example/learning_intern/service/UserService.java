package com.example.learning_intern.service;

import com.example.learning_intern.component.customUser.CustomUserDetail;
import com.example.learning_intern.model.User;
import com.example.learning_intern.payload.request.UserRequest;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.repository.UserRepository;
import com.example.learning_intern.service.transaction.CreateUserTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private CreateUserTransaction userTransaction;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder,
                       CreateUserTransaction userTransaction,
                       UserRepository userRepository
                     ) {
        this.passwordEncoder = passwordEncoder;
        this.userTransaction = userTransaction;
        this.userRepository = userRepository;
    }
    public ResponseService createUser(UserRequest userRequest) {
        if (ObjectUtils.isEmpty(userRequest)) return new ResponseService(400,null);
        User user = userRepository.findUserByEmail(userRequest.getEmail());
        String token = UUID.randomUUID().toString();
        if (ObjectUtils.isEmpty(user)) {
            String pwdE = passwordEncoder.encode(userRequest.getPassword());
            userRequest.setPassword(pwdE);

            try{
                if (!userRequest.getIsTeacher()) {
                    userTransaction.createStudent(userRequest, token);
                } else {
                    userTransaction.createTeacher(userRequest, token);
                }
                String url = " http://localhost:8080/api/v1/user/confirm-mail?token=" + token;
                String content = "Welcome to Our Website! Xác thực email của bạn bằng cách truy cập vào đường dẫn sau:                 "
                                + url;

                return new ResponseService(200, content);

            } catch (Exception e) {
                return new ResponseService(409, null);
            }
        } else return new ResponseService(408, null);
    }
   
    public static Integer getIDUserAuthenticatedFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        return  userDetail.getUserID();
    }

}
