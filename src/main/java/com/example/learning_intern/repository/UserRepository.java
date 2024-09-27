package com.example.learning_intern.repository;

import com.example.learning_intern.DTO.InstructorDTO;
import com.example.learning_intern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM `user` WHERE email = :email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    @Query(name = "InstructorDTO.Instructor", nativeQuery = true)
    List<InstructorDTO> getInstructors();


}
