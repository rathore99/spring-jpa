package com.rr.example.spring_jpa.service;

import com.rr.example.spring_jpa.exception.ResourceNotFoundException;
import com.rr.example.spring_jpa.model.User;
import com.rr.example.spring_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public List<User> getRecentUsers(LocalDate date) {
        List<User> users = userRepository.findUsersRegisteredAfter(date);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found after " + date);
        }
        return users;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

