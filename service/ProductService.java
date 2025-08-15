package com.rr.example.spring_jpa.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.rr.example.spring_jpa.repository.ProductRepository;
import com.rr.example.spring_jpa.model.Product;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product save(Product p) {
        return repo.save(p);
    }
    public void deleteByCategory(){
        repo.deleteByCategory(Product.Category.BOOKS);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public List<Product> findExpensive(BigDecimal minPrice) {
        return repo.findByPriceGreaterThan(minPrice);
    }
}

