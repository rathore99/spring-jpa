package com.rr.example.spring_jpa.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class AdvancedJUnitTest {

    @BeforeAll
    static void beforeAll() {
        // Runs once per class
    }

    @BeforeEach
    void beforeEach() {
        // Runs before each test
    }

    @AfterEach
    void afterEach() {
        // Runs after each test
    }

    @AfterAll
    static void afterAll() {
        // Runs once after all tests
    }

    @Nested
    @DisplayName("Math operations")
    class MathOps {
        @Test
        @DisplayName("addition works")
        void addition() {
            assertThat(2 + 2).isEqualTo(4);
        }

        @RepeatedTest(3)
        void repeated() {
            assertThat(Math.max(1, 2)).isEqualTo(2);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "xyz"})
    void param_value(String input) {
        assertThat(input).hasSize(3);
    }

    @ParameterizedTest
    @CsvSource({"2,3,5", "10,5,15"})
    void param_csv(int a, int b, int expected) {
        assertThat(a + b).isEqualTo(expected);
    }

    @Test
    @Timeout(1)
    void timeout() throws InterruptedException {
        // quick work under 1s
        Thread.sleep(10);
        assertThat(true).isTrue();
    }

    @Test
    void assumption() {
        boolean featureFlag = true; // imagine from env
        assumeTrue(featureFlag, "Feature disabled");
        assertThat(1).isEqualTo(1);
    }
}


