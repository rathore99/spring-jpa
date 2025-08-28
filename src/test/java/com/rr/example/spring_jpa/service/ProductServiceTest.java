package com.rr.example.spring_jpa.service;

import com.rr.example.spring_jpa.model.Product;
import com.rr.example.spring_jpa.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void setUp(TestInfo info) {
        // Demonstrate TestInfo injection
        assertThat(info.getDisplayName()).isNotNull();
    }

    @Test
    @DisplayName("save() should delegate to repository and return saved entity")
    void saveDelegatesToRepo() {
        Product toSave = new Product();
        toSave.setName("Book A");
        toSave.setCategory(Product.Category.BOOKS);
        toSave.setPrice(new BigDecimal("10.00"));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product saved = productService.save(toSave);

        verify(productRepository, times(1)).save(productCaptor.capture());
        assertThat(productCaptor.getValue()).isEqualTo(toSave);
        assertThat(saved).isEqualTo(toSave);
    }

    @Test
    void deleteByCategoryInvokesCustomDelete() {
        // act
        productService.deleteByCategory();
        // assert
        verify(productRepository).deleteByCategory(Product.Category.BOOKS);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void getAllDelegatesToFindAll() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<Product> all = productService.getAll();
        assertThat(all).isEmpty();
        verify(productRepository).findAll();
    }

    @Nested
    @DisplayName("findExpensive")
    class FindExpensive {
        @Test
        void returnsFilteredList() {
            BigDecimal min = new BigDecimal("20");
            Product premium = new Product();
            premium.setName("Premium");
            premium.setCategory(Product.Category.ELECTRONICS);
            premium.setPrice(new BigDecimal("99.99"));
            when(productRepository.findByPriceGreaterThan(min)).thenReturn(List.of(premium));

            List<Product> result = productService.findExpensive(min);
            assertThat(result).hasSize(1);
            verify(productRepository).findByPriceGreaterThan(min);
        }
    }
}


