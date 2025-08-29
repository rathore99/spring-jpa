package com.rr.example.spring_jpa.controller;

import com.rr.example.spring_jpa.service.LoggingDemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/divide")
    public ResponseEntity<String> divide(@RequestParam int a, @RequestParam int b) {
        loggingDemoService.runDemo();
        if(b == 0)
            return ResponseEntity.badRequest().body("Division by zero is not allowed");

        return ResponseEntity.ok("result of division is " +(a/b));
    }

}




