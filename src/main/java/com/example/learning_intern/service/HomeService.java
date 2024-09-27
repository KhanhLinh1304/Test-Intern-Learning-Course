package com.example.learning_intern.service;

import com.example.learning_intern.DTO.CourseDTO;
import com.example.learning_intern.DTO.InstructorDTO;
import com.example.learning_intern.DTO.SearchCourseDTO;
import com.example.learning_intern.component.jwt.JwtTokenProvider;
import com.example.learning_intern.model.Category;
import com.example.learning_intern.payload.request.SearchRQ;
import com.example.learning_intern.payload.response.HomeResponse;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.payload.response.SearchCourseResponse;
import com.example.learning_intern.repository.CategoryRepository;
import com.example.learning_intern.repository.CourseRepository;
import com.example.learning_intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public HomeService(CourseRepository courseRepository,
                       CategoryRepository categoryRepository,
                       JwtTokenProvider jwtTokenProvider,
                       UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public ResponseService homeData() {
        List<CourseDTO> courses = courseRepository.get10CourseSortByRating();
        List<Category> categories = categoryRepository.findAll();
        List<InstructorDTO> instructors = userRepository.getInstructors();
        HomeResponse homeResponse = new HomeResponse(categories, courses, null,
                null, instructors);
        return new ResponseService(200, homeResponse);
    }

    public ResponseService homeDataLoggedIn(String token) {
        Integer userID = jwtTokenProvider.getUserID(token);
        List<SimpleGrantedAuthority> roles = jwtTokenProvider.getAuthorities(token);
        List<CourseDTO> courses = courseRepository.get10CourseSortByRating();
        List<Category> categories = categoryRepository.findAll();

        boolean isStudent = roles.stream()
                            .anyMatch(r -> r.getAuthority().equals("STUDENT"));

        if (isStudent) {
            List<CourseDTO> coursesByInstructorFollowing = courseRepository.getCourseByInstructorFollowing(userID);
            List<CourseDTO> coursesByCategoryFollowing = courseRepository.getCoursesByCategoryFollowing(userID);
            List<InstructorDTO> instructors = userRepository.getInstructors();
            HomeResponse homeResponse = new HomeResponse(categories, courses, coursesByInstructorFollowing,
                    coursesByCategoryFollowing, instructors);
            return new ResponseService(200, homeResponse);
        } else {
            //coi lại
            //Instructor
            return new ResponseService(200,roles);
        }
    }
    public ResponseService searchByCourse(SearchRQ searchRQ) {
        String keyword = searchRQ.getKeyword();
        List<SearchCourseDTO> listSearch = courseRepository.searchCourseByTitle(keyword);

        if (listSearch.isEmpty()) {
            return new ResponseService(200, "không tìm thấy khoá học nào");
        } else {
            SearchCourseResponse response = new SearchCourseResponse(listSearch);
            return new ResponseService(200, response);
        }
    }


}
