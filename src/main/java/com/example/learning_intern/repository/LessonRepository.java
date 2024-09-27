package com.example.learning_intern.repository;

import com.example.learning_intern.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findLessonsBySectionID(Integer sectionID);


}
