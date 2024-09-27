package com.example.learning_intern.payload.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseRQ {
    private String title;
    private String description;
    private Integer categoryID;
    private Double price;
    private String url;
    private List<SectionRQ> sections;

    public List<LessonRQ> getLessonFromSection() {
        List<LessonRQ> listLessons = new ArrayList<>();
        if (sections != null) {
            for (SectionRQ section : sections) {
                if (section.getLessons() != null) {
                    listLessons.addAll(section.getLessons()) ;
                }
            }
        }
            return listLessons;

    }
}
