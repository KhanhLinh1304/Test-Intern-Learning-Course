package com.example.learning_intern.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@NamedNativeQuery(name = "CourseInstructor.CourseInstructorDTO",
        query = "SELECT c.id, c.url, c.title, c.rating, " +
                "c.number_of_student as numberOfStudent, " +
                "ct.name AS categoryName " +
                "FROM course c " +
                "INNER JOIN  user_course uc on c.id = uc.course_id " +
                "INNER JOIN user u on u.id = uc.user_id " +
                "INNER JOIN category ct on ct.id = c.category_id " +
                "WHERE u.id = :userID " +
                "ORDER BY created_at desc ",
        resultSetMapping = "Mapping.CourseInstructorDTO"
                )

@SqlResultSetMapping( name = "Mapping.CourseInstructorDTO",
        classes = @ConstructorResult( targetClass = CourseInstructorDTO.class,
                columns = {
                        @ColumnResult( name = "id", type = Integer.class),
                        @ColumnResult( name = "url", type = String.class),
                        @ColumnResult( name = "title", type = String.class),
                        @ColumnResult( name = "categoryName", type = String.class),
                        @ColumnResult( name = "rating", type = Double.class),
                        @ColumnResult( name = "numberOfStudent", type = Integer.class)
                }))
@Data
public class CourseInstructorDTO {
    @Id
    private Integer id;
    private String url;
    private String title;
    private Double rating;
    private Integer numberOfStudent;
    private String categoryName;
}
