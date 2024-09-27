package com.example.learning_intern.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@NamedNativeQuery(name = "CourseDTO.FilterTenCourse",
        query = "SELECT c.id, c.url, c.title, c.rating, " +
                "c.number_of_student as numberOfStudent, u.name as instructorName " +
                "FROM course c " +
                "INNER JOIN  user_course uc on c.id = uc.course_id " +
                "INNER JOIN user u on u.id = uc.user_id " +
                "ORDER BY c.rating DESC " +
                "LIMIT 10 ",
        resultSetMapping = "Mapping.FilterTenCourse")

@SqlResultSetMapping(name = "Mapping.FilterTenCourse",
        classes = @ConstructorResult( targetClass = CourseDTO.class,
                columns = {
                            @ColumnResult( name = "id", type = Integer.class),
                            @ColumnResult( name = "url", type = String.class),
                            @ColumnResult( name = "title", type = String.class),
                            @ColumnResult( name = "instructorName", type = String.class),
                            @ColumnResult( name = "rating", type = Double.class),
                            @ColumnResult( name = "numberOfStudent", type = Integer.class)
}))

@NamedNativeQuery(name = "CourseDTO.InstructorFollowing",
        query = "SELECT c.id, c.url, c.title, c.rating, " +
                "c.number_of_student as numberOfStudent, u.name as instructorName " +
                "FROM user_course uc " +
                "INNER JOIN course c on uc.course_id = c.id " +
                "INNER JOIN teacher_following t on uc.user_id = t.teacher_id " +
                "INNER JOIN `user` u on uc.user_id = u.id " +
                "where t.student_id = :userID " +
                "LIMIT 5",
        resultSetMapping = "Mapping.InstructorFollowing")

@SqlResultSetMapping( name = "Mapping.InstructorFollowing",
        classes = @ConstructorResult( targetClass = CourseDTO.class,
                columns = {
                        @ColumnResult( name = "id", type = Integer.class),
                        @ColumnResult( name = "url", type = String.class),
                        @ColumnResult( name = "title", type = String.class),
                        @ColumnResult( name = "instructorName", type = String.class),
                        @ColumnResult( name = "rating", type = Double.class),
                        @ColumnResult( name = "numberOfStudent", type = Integer.class)
                }))

@NamedNativeQuery(name = "CourseDTO.CategoryFollowing",
        query = "SELECT c.id, c.url, c.title, c.rating, " +
                "c.number_of_student as numberOfStudent, u.name as instructorName " +
                "FROM course c " +
                "INNER JOIN category_following cf ON c.category_id = cf.category_id " +
                "INNER JOIN user_course uc ON uc.course_id = c.id " +
                "INNER JOIN `user` u ON uc.user_id = u.id\n" +
                "WHERE cf.user_id = :userID " +
                "LIMIT 5",
        resultSetMapping = "Mapping.CategoryFollowing")

@SqlResultSetMapping( name = "Mapping.CategoryFollowing",
        classes = @ConstructorResult( targetClass = CourseDTO.class,
                columns = {
                        @ColumnResult( name = "id", type = Integer.class),
                        @ColumnResult( name = "url", type = String.class),
                        @ColumnResult( name = "title", type = String.class),
                        @ColumnResult( name = "instructorName", type = String.class),
                        @ColumnResult( name = "rating", type = Double.class),
                        @ColumnResult( name = "numberOfStudent", type = Integer.class)
                }))

@Data
public class CourseDTO {
    @Id
    private Integer id;
    private String url;
    private String title;
    private String instructorName;
    private Double rating;
    private Integer numberOfStudent;


}
