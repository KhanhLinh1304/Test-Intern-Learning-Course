package com.example.learning_intern.DTO;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@NamedNativeQuery(name = "SearchCourseDTO.SearchCourse",
            query = "SELECT  c.title, c.id " +
                    "FROM course c " +
                    "WHERE c.title LIKE CONCAT('%', :keyword, '%') " +
                    "ORDER BY c.rating DESC ", resultSetMapping = "Mapping.SearchCourse")

@SqlResultSetMapping(name = "Mapping.SearchCourse",
classes = @ConstructorResult(targetClass = SearchCourseDTO.class,
        columns = {
                @ColumnResult(name = "id", type = Integer.class),
                @ColumnResult(name = "title", type = String.class)
        }))
@Data
public class SearchCourseDTO {
    @Id
    private Integer id;
    private String title;
}
