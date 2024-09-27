package com.example.learning_intern.service;

import com.example.learning_intern.model.MailAuthentication;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.repository.MailAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class MailAuthenticationService {
    private MailAuthenticationRepository mailAuthenticationRepository;
    @Autowired
    public MailAuthenticationService(MailAuthenticationRepository mailAuthenticationRepository) {
        this.mailAuthenticationRepository = mailAuthenticationRepository;
    }

    public ResponseService confirmMail(String token) {
        try {
            if (token.isEmpty()) return new ResponseService(400, null);
            MailAuthentication mailAuth = mailAuthenticationRepository.isExistToken(token);
            if(Objects.isNull(mailAuth)) return new ResponseService(404, null);
            else {
                mailAuth.setIsConfirmedMail(1);
                mailAuth.setConfirmCompletedAt(LocalDateTime.now());
                mailAuthenticationRepository.save(mailAuth);
            }
        } catch (Exception ex) {
            return new ResponseService(407, null);
        }
        return new ResponseService(200, "Xác thực email thành công!!!");

    }
}
