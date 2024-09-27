package com.example.learning_intern.payload.response;

import com.example.learning_intern.DTO.SearchCourseDTO;
import lombok.Data;

import java.util.List;
@Data
public class SearchCourseResponse {
    private List<SearchCourseDTO> searchCourseDTOS;
    private Integer total;

    public SearchCourseResponse(List<SearchCourseDTO> courseDTOS) {
        this.searchCourseDTOS = courseDTOS;
        this.total = courseDTOS.size();
    }
}
