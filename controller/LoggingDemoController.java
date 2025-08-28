package com.rr.example.spring_jpa.controller;

import com.rr.example.spring_jpa.service.LoggingDemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LoggingDemoController {

    private final LoggingDemoService loggingDemoService;

    public LoggingDemoController(LoggingDemoService loggingDemoService) {
        this.loggingDemoService = loggingDemoService;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        loggingDemoService.runDemo();
        return ResponseEntity.ok("Logged demo events. Check console and logs.");
    }
}




