package com.rr.example.spring_jpa.repository;

import com.rr.example.spring_jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived Query Method
    List<Product> findByCategory(Product.Category category);

    // Derived Query Method with condition
    List<Product> findByPriceGreaterThan(BigDecimal price);
    @Query("DELETE FROM Product p WHERE p.category = :category")
    void deleteByCategory(Product.Category category);

    // JPQL Query
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchByName(String keyword);

    // Native SQL Query
    @Query(value = "SELECT * FROM products WHERE price < :maxPrice", nativeQuery = true)
    List<Product> findCheapProducts(BigDecimal maxPrice);
}
