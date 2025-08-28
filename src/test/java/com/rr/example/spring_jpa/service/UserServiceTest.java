package com.rr.example.spring_jpa.service;

import com.rr.example.spring_jpa.exception.ResourceNotFoundException;
import com.rr.example.spring_jpa.model.User;
import com.rr.example.spring_jpa.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUser_returnsEntity_whenFound() {
        User u = new User( "Rahul","rahul123@gmail.com", LocalDate.parse("2024-01-01"));
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        User result = userService.getUser(1L);

        assertThat(result).isEqualTo(u);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUser_throws_whenMissing() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> userService.getUser(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Nested
    @DisplayName("getRecentUsers")
    class GetRecentUsers {
        @Test
        void returnsList_whenFound() {
            LocalDate date = LocalDate.parse("2024-05-01");
            when(userRepository.findUsersRegisteredAfter(date))
                    .thenReturn(List.of(new User("A", "apple@gmail.com", date.plusDays(1))));

            List<User> users = userService.getRecentUsers(date);
            assertThat(users).hasSize(1);
            verify(userRepository).findUsersRegisteredAfter(date);
        }

        @Test
        void throws_whenEmpty() {
            LocalDate date = LocalDate.parse("2024-05-01");
            when(userRepository.findUsersRegisteredAfter(date)).thenReturn(List.of());
            assertThatThrownBy(() -> userService.getRecentUsers(date))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("No users");
        }
    }

    @Test
    void saveUser_delegatesToRepository() {
        User toSave = new User((Long) null, "X", LocalDate.now());
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        User saved = userService.saveUser(toSave);
        assertThat(saved).isEqualTo(toSave);
        verify(userRepository).save(toSave);
    }
}


