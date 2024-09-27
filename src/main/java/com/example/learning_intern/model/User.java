package com.example.learning_intern.model;

import com.example.learning_intern.payload.request.UserRequest;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_desc")
    private String profileDesc;

    private Integer gender;

    private Integer role;

    private String position;

    public User(UserRequest userRequest){
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.gender = userRequest.getGender();
        this.photoUrl = userRequest.getPhotoUrl();
        this.phoneNumber = userRequest.getPhoneNumber();
        this.role = userRequest.getRole();
        this.profileDesc = userRequest.getProfileDesc();
        this.position = userRequest.getPosition();
    }

    public User() {

    }
}

