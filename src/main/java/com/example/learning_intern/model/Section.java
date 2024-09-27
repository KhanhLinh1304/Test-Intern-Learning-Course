package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseID;

    private String title;

    @Column(name = "number_of_lesson")
    private Integer numberOfLesson;

    public Section(Integer courseID, String title) {
        this.courseID = courseID;
        this.title = title;
        this.numberOfLesson = 0;
    }

    public Section() {

    }
}
