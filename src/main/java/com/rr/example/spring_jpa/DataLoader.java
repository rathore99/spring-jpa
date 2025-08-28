//package com.rr.example.spring_jpa;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import com.rr.example.spring_jpa.model.Product;
//import com.rr.example.spring_jpa.repository.ProductRepository;
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final ProductRepository repo;
//
//    public DataLoader(ProductRepository repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public void run(String... args) {
//        repo.save(new Product(1L, "Laptop", new BigDecimal("80000"), Product.Category.ELECTRONICS, new Date()));
//        repo.save(new Product(2L, "Book - Java", new BigDecimal("500"), Product.Category.BOOKS, new Date()));
//        repo.save(new Product(3L, "T-Shirt", new BigDecimal("999"), Product.Category.CLOTHING, new Date()));
//    }
//}
