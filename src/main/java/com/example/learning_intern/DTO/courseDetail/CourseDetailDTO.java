package com.example.learning_intern.DTO.courseDetail;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@NamedNativeQuery(name = "CourseDetailDTO.CourseDetail",
query = "SELECT c.id, c.title, c.description, c.rating, c.price, c.url, " +
        "c.number_of_student AS numberOfStudent, c.total_time AS totalTime, " +
        "u.name AS instructorName, ct.name AS categoryName " +
        "FROM course c " +
        "INNER JOIN user_course uc ON c.id = uc.course_id " +
        "INNER JOIN user u ON uc.user_id = u.id " +
        "INNER JOIN category ct ON c.category_id = ct.id " +
        "WHERE c.id = :courseID ",
        resultSetMapping = "Mapping.CourseDetailDTO")


@SqlResultSetMapping(name = "Mapping.CourseDetailDTO",
classes = @ConstructorResult(targetClass = CourseDetailDTO.class,
columns = {
        @ColumnResult( name = "id", type = Integer.class),
        @ColumnResult( name = "title", type = String.class),
        @ColumnResult( name = "description", type = String.class),
        @ColumnResult( name = "rating", type = Double.class),
        @ColumnResult( name = "numberOfStudent", type = Integer.class),
        @ColumnResult( name = "instructorName", type = String.class),
        @ColumnResult( name = "totalTime", type = Integer.class),
        @ColumnResult( name = "categoryName", type = String.class),
        @ColumnResult( name = "price", type = Double.class),
        @ColumnResult( name = "url", type = String.class),

}))
@Data
public class CourseDetailDTO {
    @Id
    private Integer id;
    private String categoryName;
    private String instructorName;
    private String title;
    private String description;
    private Double rating;
    private Integer numberOfStudent;
    private Integer totalTime;
    private Double price;
    private String url;
}
