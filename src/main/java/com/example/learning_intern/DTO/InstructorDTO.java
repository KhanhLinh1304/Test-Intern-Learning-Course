package com.example.learning_intern.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@NamedNativeQuery(name = "InstructorDTO.Instructor",
query = "SELECT u.id, u.name, u.position " +
        "FROM user u " +
        "WHERE u.role = 1",
resultSetMapping = "Mapping.InstructorDTO")

@SqlResultSetMapping(name = "Mapping.InstructorDTO",
        classes = @ConstructorResult( targetClass = InstructorDTO.class,
                            columns = {
                                    @ColumnResult(name = "id", type = Integer.class),
                                    @ColumnResult(name = "name", type = String.class),
                                    @ColumnResult(name = "position", type = String.class)

}))
@Data
public class InstructorDTO {
    @Id
    private Integer id;
    private String name;
    private String position;
}
