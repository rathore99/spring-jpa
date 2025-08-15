package com.rr.example.spring_jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // Marks this class as a JPA entity (table)
@Table(name = "products") // Maps to "products" table
@Data // Lombok: generates getters/setters/toString/hashCode
@NoArgsConstructor
@AllArgsConstructor
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
    private java.util.Date createdAt;


    public enum Category {
        ELECTRONICS, BOOKS, CLOTHING
    }
}
