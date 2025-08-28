package com.rr.example.spring_jpa.repository;

import com.rr.example.spring_jpa.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserCustomRepository {
    List<User> findUsersRegisteredAfter(LocalDate date);
}
