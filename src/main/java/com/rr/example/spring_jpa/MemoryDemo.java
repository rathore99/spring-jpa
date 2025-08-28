package com.rr.example.spring_jpa;

import java.util.Arrays;

public class MemoryDemo {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            byte[] data = new byte[1024 * 1024]; // 1 MB allocation
            Thread.sleep(10);
        }
        Arrays.asList(
                "Hello",
                "World",
                "This",
                "Is",
                "A",
                "Memory",
                "Demo"
        ).stream().filter(
                s -> s.length() > 3
        ).forEach(
                System.out::println
        );
    }
}

