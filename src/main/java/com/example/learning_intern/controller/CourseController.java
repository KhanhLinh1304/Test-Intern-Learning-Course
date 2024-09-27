package com.example.learning_intern.controller;

import com.example.learning_intern.payload.response.APIResponse;
import com.example.learning_intern.payload.response.ResponseFormat;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {
    private final CourseService courseService;
    private final ResponseFormat format;

    @Autowired
    public CourseController(CourseService courseService, ResponseFormat format) {
        this.courseService = courseService;
        this.format = format;
    }

    @GetMapping(value = "/{courseID}")
    public ResponseEntity<APIResponse> getCourseDetail(@PathVariable Integer courseID) {
        if (courseID == null ) return format.badRequest("BAD REQUEST");
        ResponseService rs = courseService.getCourseDetail(courseID);
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 404) return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }
}
