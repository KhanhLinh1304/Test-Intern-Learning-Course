package com.example.learning_intern.component.customUser;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data
public class CustomUserDetail implements UserDetails {
    private String email;
    private String password;
    private Integer userID;
    private final Collection<? extends GrantedAuthority> authorities ;


    public CustomUserDetail(String email, String password, Integer userID,
                            Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.userID = userID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email    ;
    }



}
