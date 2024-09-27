package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryID;

    private String title;

    private String description;

    private Double rating;

    @Column(name = "number_of_student")
    private Integer numberOfStudent;

    @Column(name = "total_time")
    private Integer totalTime;

    @Column(name = "price")
    private Double price;

    private String url;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Course(Integer categoryID, String title, String description, Double price, String url) {
        this.categoryID = categoryID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.url = url;
        this.numberOfStudent = 0;
        this.createdAt = LocalDate.now();
    }

    public Course() {

    }
}
