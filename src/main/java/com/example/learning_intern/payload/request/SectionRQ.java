package com.example.learning_intern.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class SectionRQ {
    private String title;
    private List<LessonRQ> lessons;
}
