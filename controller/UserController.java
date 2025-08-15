package com.rr.example.spring_jpa.controller;

import com.rr.example.spring_jpa.model.User;
import com.rr.example.spring_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/registered-after/{date}")
    public List<User> getUsersAfter(@PathVariable String date) {
        return userService.getRecentUsers(LocalDate.parse(date));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}

