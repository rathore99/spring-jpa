package com.rr.example.spring_jpa.controller;

import com.rr.example.spring_jpa.model.Product;
import com.rr.example.spring_jpa.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    void getAll_returnsOkAndList() throws Exception {
        Product p1 = new Product();
        p1.setName("P1");
        p1.setCategory(Product.Category.BOOKS);
        p1.setPrice(new BigDecimal("10.00"));
        List<Product> products = List.of(p1);
        BDDMockito.given(productService.getAll()).willReturn(products);

        String expectedJson = objectMapper.writeValueAsString(products);

        mockMvc.perform(get("/api/products").param("version", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void create_returnsOk() throws Exception {
        Product p = new Product();
        p.setName("P1");
        p.setCategory(Product.Category.BOOKS);
        p.setPrice(new BigDecimal("10.00"));
        BDDMockito.given(productService.save(BDDMockito.any())).willReturn(p);

        String body = objectMapper.writeValueAsString(p);
        mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("P1")));
    }

    @Test
    void getExpensive_returnsOk() throws Exception {
        Product p2 = new Product();
        p2.setName("P2");
        p2.setCategory(Product.Category.ELECTRONICS);
        p2.setPrice(new BigDecimal("100.00"));
        List<Product> products = List.of(p2);
        BDDMockito.given(productService.findExpensive(new BigDecimal("50"))).willReturn(products);

        mockMvc.perform(get("/api/products/expensive").param("minPrice", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("P2")));
    }
}


