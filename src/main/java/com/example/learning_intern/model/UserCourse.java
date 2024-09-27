package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_course")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseID;

    @Column(name = "user_id")
    private Integer userID;

    public UserCourse(Integer courseID, Integer userID) {
        this.courseID = courseID;
        this.userID = userID;
    }

    public UserCourse() {

    }
}
