package com.rr.example.spring_jpa.controller;

import com.rr.example.spring_jpa.exception.ResourceNotFoundException;
import com.rr.example.spring_jpa.model.Product;
import com.rr.example.spring_jpa.response.ErrorResponse;
import com.rr.example.spring_jpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product p) {
        Product p1 = service.save(p);
        if (p1 != null) {
            return ResponseEntity.ok(p1);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping(params = "version=1")
    public List<Product> getAll() {
        return service.getAll();
    }
    @GetMapping(value = "/{id}")
    public String get(@PathVariable int id) throws Exception {
        System.out.println("Fetching product with ID: " + id);
        throw new Exception("Security Exception occurred while fetching product with ID: " + id);
      //  return "Not Found";
    }
    @DeleteMapping("/{category}")
    public boolean delete(@PathVariable String category) {
         service.deleteByCategory();
         return true;
    }



    @GetMapping("/expensive")
    public List<Product> getExpensive(@RequestParam BigDecimal minPrice) {
        return service.findExpensive(minPrice);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>("Product COntrolerr error"+error, HttpStatus.NOT_FOUND);
    }


}
