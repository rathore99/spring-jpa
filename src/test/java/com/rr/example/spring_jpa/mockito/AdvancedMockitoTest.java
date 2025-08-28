package com.rr.example.spring_jpa.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvancedMockitoTest {

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Spy
    private List<String> spyList = new ArrayList<>();

    @Test
    void captor_and_spy_demo() {
        spyList.add("a");
        spyList.add("b");
        verify(spyList, times(2)).add(stringCaptor.capture());
        assertThat(stringCaptor.getAllValues()).containsExactly("a", "b");
        assertThat(spyList).hasSize(2);
    }

    interface Greeter {
        String greet(String name);
    }

    @Test
    void answers_and_verification_modes() {
        Greeter greeter = mock(Greeter.class);
        when(greeter.greet(anyString())).thenAnswer(inv -> "Hello, " + inv.getArgument(0));

        String msg = greeter.greet("Rahul");
        assertThat(msg).isEqualTo("Hello, Rahul");

        verify(greeter, atLeastOnce()).greet("Rahul");
        verify(greeter, never()).greet("Unknown");
        verifyNoMoreInteractions(greeter);
    }
}


