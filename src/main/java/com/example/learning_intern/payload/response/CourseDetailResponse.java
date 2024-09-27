package com.example.learning_intern.payload.response;

import com.example.learning_intern.DTO.courseDetail.CourseDetailDTO;
import com.example.learning_intern.model.Lesson;
import com.example.learning_intern.model.Section;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailResponse {
    private CourseDetailDTO courseDetailDTO;
    private List<Section> sections;
    private List<Lesson> lessons;

    public CourseDetailResponse(CourseDetailDTO courseDetailDTO,
                                List<Section> sections, List<Lesson> lessons) {
        this.courseDetailDTO = courseDetailDTO;
        this.sections = sections;
        this.lessons = lessons;
    }
}
