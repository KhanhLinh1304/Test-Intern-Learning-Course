package com.example.learning_intern.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private Boolean isTeacher;
    private String name;
    private String photoUrl;
    private String email;
    private String password;
    private String phoneNumber;
    private String profileDesc;
    private Integer gender;
    private Integer role;
    private List<Integer> categoryIDs;
    private List<Integer> teacherIDs;
    private String position;

}
