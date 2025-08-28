package com.rr.example.spring_jpa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity // Marks this class as a JPA entity (table)
@Table(name = "products") // Maps to "products" table
public class Product {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment in DB
    private Long id;

    @Column(nullable = false, unique = true, length = 100) // DB column constraints
    private String name;

    @Column(precision = 10, scale = 2) // Decimal(10,2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING) // Store enum as string in DB
    private Category category;

    @Temporal(TemporalType.TIMESTAMP) // Store date-time
    private Date createdAt;


    public enum Category {
        ELECTRONICS, BOOKS, CLOTHING
    }

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, Category category, Date createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
