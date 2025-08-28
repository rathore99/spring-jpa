package com.rr.example.spring_jpa.repository;

import com.rr.example.spring_jpa.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUsersRegisteredAfter(LocalDate date) {
        String jpql = "SELECT u FROM User u WHERE u.registrationDate > :date";
        return entityManager.createQuery(jpql, User.class).setParameter("date", date).getResultList();
    }
}

