package com.example.learning_intern.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Data;

@NamedNativeQuery(name = "ConfirmMail.MailAuthenDTO",
                query = "SELECT m.token , u.email " +
                        "FROM mail_authentication m " +
                        "INNER JOIN `user` u on u.id = m.user_id " +
                        "WHERE m.user_id = :userID ORDER BY token_sent_at DESC " +
                        "LIMIT 1",
resultSetMapping = "Mapping.MailAuthenDTO")
@SqlResultSetMapping( name = "Mapping.MailAuthenDTO",
        classes = @ConstructorResult(targetClass = MailAuthenticationDTO.class,
                columns = { @ColumnResult( name = "token", type = String.class),
                        @ColumnResult( name = "email", type = String.class)})
)
@Data
public class MailAuthenticationDTO {
    private String token;
    private String email;
}
