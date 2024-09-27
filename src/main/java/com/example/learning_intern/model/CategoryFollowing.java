package com.example.learning_intern.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category_following")
public class CategoryFollowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryID;

    @Column(name = "user_id")
    private Integer userID;

    public CategoryFollowing(Integer categoryID, Integer userID) {
        this.categoryID = categoryID;
        this.userID = userID;
    }

    public CategoryFollowing() {

    }
}
