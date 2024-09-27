package com.example.learning_intern.repository;

import com.example.learning_intern.model.MailAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MailAuthenticationRepository extends JpaRepository<MailAuthentication, Integer> {
//    @Query(name = "ConfirmMail.MailAuthenDTO", nativeQuery = true)
//    MailAuthenticationDTO getTokenConfirmMail(Integer userID);
    @Query("SELECT m FROM MailAuthentication m WHERE m.token = :token")
    MailAuthentication isExistToken(@Param("token") String token);


}
