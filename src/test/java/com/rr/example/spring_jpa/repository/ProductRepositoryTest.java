package com.rr.example.spring_jpa.repository;

import com.rr.example.spring_jpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_and_findByCategory() {
        Product p = new Product();
        p.setName("Book A");
        p.setCategory(Product.Category.BOOKS);
        p.setPrice(new BigDecimal("15.00"));
        productRepository.save(p);
        List<Product> books = productRepository.findByCategory(Product.Category.BOOKS);
        assertThat(books).extracting(Product::getCategory).containsOnly(Product.Category.BOOKS);
    }

    @Test
    void findByPriceGreaterThan_returnsFiltered() {
        Product cheap = new Product();
        cheap.setName("Cheap");
        cheap.setCategory(Product.Category.BOOKS);
        cheap.setPrice(new BigDecimal("5"));
        productRepository.save(cheap);
        Product expensive = new Product();
        expensive.setName("Expensive");
        expensive.setCategory(Product.Category.ELECTRONICS);
        expensive.setPrice(new BigDecimal("100"));
        productRepository.save(expensive);
        List<Product> result = productRepository.findByPriceGreaterThan(new BigDecimal("50"));
        assertThat(result).extracting(Product::getName).contains("Expensive").doesNotContain("Cheap");
    }
}


