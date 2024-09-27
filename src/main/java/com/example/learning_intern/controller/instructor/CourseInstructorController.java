package com.example.learning_intern.controller.instructor;

import com.example.learning_intern.payload.request.CourseRQ;
import com.example.learning_intern.payload.response.APIResponse;
import com.example.learning_intern.payload.response.ResponseFormat;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.service.CourseService;
import com.example.learning_intern.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
public class CourseInstructorController {
    private final InstructorService instructorService;
    private final ResponseFormat format;
    private final CourseService courseService;

    @Autowired
    public CourseInstructorController(InstructorService instructorService,
                                      ResponseFormat format,
                                      CourseService courseService) {
        this.instructorService = instructorService;
        this.format = format;
        this.courseService = courseService;
    }

    @GetMapping(value = "/myCourse")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public ResponseEntity<APIResponse> getCourseByUserID() {
        ResponseService rs = instructorService.getCourseByInstructorID();
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 404) return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }
    @PostMapping(value = "/addCourse")
    public ResponseEntity<APIResponse> addCourse(@RequestBody CourseRQ courseRQ) {
        ResponseService rs = courseService.addCourse(courseRQ);
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 404) return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }
}
