package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "section_id")
    private Integer sectionID;

    private String title;
    private String description;
    private Integer duration;

    @Column(name = "video_url")
    private String videoURL;

    public Lesson(Integer sectionID, String title, String videoURL) {
        this.sectionID = sectionID;
        this.title = title;
        this.videoURL = videoURL;
        this.duration = 0;

    }

    public Lesson() {

    }
}
