package com.example.learning_intern.service.transaction;

import com.example.learning_intern.model.Course;
import com.example.learning_intern.model.Lesson;
import com.example.learning_intern.model.Section;
import com.example.learning_intern.model.UserCourse;
import com.example.learning_intern.payload.request.LessonRQ;
import com.example.learning_intern.payload.request.SectionRQ;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Repository
public class CreateCourseTransaction {
    private EntityManager entityManager;

    @Autowired
    public CreateCourseTransaction(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Integer countLessonOfSection(Integer sectionID) {
        TypedQuery<Long> theQuery = entityManager.createQuery(
                "SELECT COUNT(l.id) AS numberLesson FROM Lesson l WHERE l.sectionID = :sectionID", Long.class);
        theQuery.setParameter("sectionID", sectionID);
        return theQuery.getSingleResult().intValue();
    }

    public Integer getLastInsertedCourse() {
        Course course = entityManager.createQuery(
                "SELECT c FROM Course c ORDER BY id DESC LIMIT 1", Course.class).getSingleResult();
        return course.getId();
    }

    public Integer getLastInsertedSection() {
        Section section = entityManager.createQuery(
                "SELECT s FROM Section s ORDER BY id DESC LIMIT 1", Section.class).getSingleResult();
        return section.getId();
    }

    @Transactional
    public void updateNumberLesson(Integer numberOfLesson, Integer sectionID) {
        try {
            Query query = entityManager.createQuery(
                    "UPDATE Section s SET s.numberOfLesson = :numberOfLesson WHERE s.id = :sectionID ");
            query.setParameter("numberOfLesson", numberOfLesson);
            query.setParameter("sectionID", sectionID);
            // Execute the update
            int rowsUpdated = query.executeUpdate();

            // Optionally, you can log or handle the result of rowsUpdated
            if (rowsUpdated == 0) {
                throw new RuntimeException("No Section IS UPDATED");
            }
        } catch (Exception e) {
            throw new RuntimeException("CONFLICT");
        }
    }
    @Transactional
    public void createCourse(Course course, List<SectionRQ> sections, Integer userID) {
        try {

            entityManager.persist(course);
            Integer courseID = getLastInsertedCourse();
            UserCourse userCourse = new UserCourse(courseID, userID);
            entityManager.persist(userCourse);
            for (SectionRQ sectionRQ : sections) {

                Section section = new Section(courseID, sectionRQ.getTitle());
                entityManager.persist(section);

                Integer sectionID = getLastInsertedSection();
                List<LessonRQ> lessonRQS = sectionRQ.getLessons();

                for (LessonRQ lessonRQ : lessonRQS) {
                    Lesson lesson = new Lesson(sectionID, lessonRQ.getTitle(), lessonRQ.getUrlVideo());
                    entityManager.persist(lesson);
                }

                Integer numberLessonOfSection = countLessonOfSection(sectionID);
                System.out.println(numberLessonOfSection);
                updateNumberLesson(numberLessonOfSection, sectionID);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("CONFLICT");
        }
    }
}
