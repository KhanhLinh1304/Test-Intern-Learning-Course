package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mail_authentication")
public class MailAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "token_sent_at")
    private LocalDateTime tokenSentAt;

    private String token;

    @Column(name = "is_confirmed_mail")
    private Integer isConfirmedMail = 0;

    @Column(name = "is_approved")
    private Integer isApproved = 0;

    @Column(name = "confirm_completed_at")
    private LocalDateTime confirmCompletedAt;

    @Column(name = "approve_completed_at")
    private LocalDateTime approveCompletedAt;

    public MailAuthentication(Integer userID, String token) {
        this.userID = userID;
        this.tokenSentAt = LocalDateTime.now();
        this.token = token;
    }

    public MailAuthentication() {

    }
}
