package com.example.learning_intern.controller;

import com.example.learning_intern.component.jwt.JwtTokenProvider;
import com.example.learning_intern.payload.request.SearchRQ;
import com.example.learning_intern.payload.response.APIResponse;
import com.example.learning_intern.payload.response.ResponseFormat;
import com.example.learning_intern.payload.response.ResponseService;
import com.example.learning_intern.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
public class HomeController {
    private final HomeService homeService;
    private final ResponseFormat format;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public HomeController(HomeService homeService,
                          ResponseFormat format,
                          JwtTokenProvider jwtTokenProvider) {
        this.homeService = homeService;
        this.format = format;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = "/home")
    public ResponseEntity<APIResponse> home(@RequestHeader(value = "Authorization", required = false)
                                                String authorizationHeader) {
        ResponseService rs = null;
        String token = "";
        if (authorizationHeader == null || authorizationHeader.isEmpty() ) {
            rs = homeService.homeData();
        } else {
            if (authorizationHeader.startsWith("Bearer ")) {
                 token = authorizationHeader.substring(7);
                if (jwtTokenProvider.validateToken(token)) {
                    rs = homeService.homeDataLoggedIn(token);
                } else return format.notFound("token invalid");
            }
        }
        assert rs != null;
        return getApiResponseResponseEntity(rs, format);
    }

    @PostMapping(value = "/searchCourse")
    public ResponseEntity<APIResponse> searchCourse(@RequestBody SearchRQ searchRequest) {
        if (searchRequest.getKeyword() == null ) {
            return format.badRequest("BAD INPUT");
        }
        ResponseService rs = homeService.searchByCourse(searchRequest);
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        return format.confictRequest("CONFLICT");
    }

    static ResponseEntity<APIResponse> getApiResponseResponseEntity(ResponseService rs, ResponseFormat format) {
        if (rs.getCode() == 200 ) return format.success(rs.getObject());
        if (rs.getCode() == 400 )return format.badRequest("BAD INPUT");
        if (rs.getCode() == 409 )return format.confictRequest("CONFLICT");
        if (rs.getCode() == 404 )return format.notFound("NOT FOUND");
        return null;
    }


}


