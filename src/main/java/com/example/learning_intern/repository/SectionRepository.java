package com.example.learning_intern.repository;

import com.example.learning_intern.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    List<Section> findAllByCourseID(Integer courseID);
}
