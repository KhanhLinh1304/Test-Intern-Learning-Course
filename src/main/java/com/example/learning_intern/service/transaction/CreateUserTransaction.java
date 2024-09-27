package com.example.learning_intern.service.transaction;

import com.example.learning_intern.model.CategoryFollowing;
import com.example.learning_intern.model.InstructorFollowing;
import com.example.learning_intern.model.MailAuthentication;
import com.example.learning_intern.model.User;
import com.example.learning_intern.payload.request.UserRequest;
import com.example.learning_intern.payload.response.ResponseService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;

@Repository
public class CreateUserTransaction {
    private EntityManager entityManager;
    @Autowired
    public CreateUserTransaction(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Integer getLastInsertedUserID() {
        User theQuery = entityManager.createQuery(
                "select u from User u order by id desc limit 1", User.class).getSingleResult();
        return theQuery.getId();
    }
    @Transactional
    public void createTeacherFollowing(Integer teacherID){
        Integer userID = getLastInsertedUserID();

        InstructorFollowing teacherFollowing = new InstructorFollowing(teacherID, userID);
        entityManager.persist(teacherFollowing);
    }

    @Transactional
    public void createCategoryFollowing(Integer categoryID){
        Integer userID = getLastInsertedUserID();
        CategoryFollowing categoryFollowing = new CategoryFollowing(categoryID, userID);

        entityManager.persist(categoryFollowing);
    }
    @Transactional
    public void createStudent(UserRequest userRequest, String token) {
        if(ObjectUtils.isEmpty(userRequest))  new ResponseService(404, null);
        try {
            User user = new User(userRequest);
            entityManager.persist(user);
            for (Integer categoryID : userRequest.getCategoryIDs()) {
                createCategoryFollowing(categoryID);
            }
            for (Integer teacherID : userRequest.getTeacherIDs()) {
                createTeacherFollowing(teacherID);
            }
            MailAuthentication mailAuthentication = new MailAuthentication(getLastInsertedUserID(), token);
            entityManager.persist(mailAuthentication);

        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("conflict");
        }
    }

    @Transactional
    public void createTeacher(UserRequest userRequest, String token){
        if(ObjectUtils.isEmpty(userRequest))  new ResponseService(404, null);

        try {
            User user = new User(userRequest);
            entityManager.persist(user);

//            for (Integer categoryID : userRequest.getCategoryIDs()) {
//                createCategoryFollowing(categoryID);
//            }
            MailAuthentication mailAuthentication = new MailAuthentication(getLastInsertedUserID(), token);
            entityManager.persist(mailAuthentication);
        } catch(Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("conflict");
        }
    }

}
