package com.rr.example.spring_jpa.repository;

import com.rr.example.spring_jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long>, UserCustomRepository {
}

