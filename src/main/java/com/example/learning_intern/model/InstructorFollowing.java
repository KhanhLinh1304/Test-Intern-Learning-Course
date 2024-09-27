package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teacher_following")
public class InstructorFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacher_id")
    private Integer teacherID;

    @Column(name = "student_id")
    private Integer studentID;
    public InstructorFollowing(Integer teacherID, Integer studentID){
        this.teacherID = teacherID;
        this.studentID = studentID;
    }


    public InstructorFollowing() {

    }
}
