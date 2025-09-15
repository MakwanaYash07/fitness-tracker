package com.fitness.tracker.service;

import com.fitness.tracker.dao.UserDao;
import com.fitness.tracker.model.User;
import com.fitness.tracker.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * @author Yash
 */

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_success() {
        User user = new User();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword("rawPassword");

        // stub password encoder
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword123");

        when(userDao.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        User saved = userService.createUser(user);

        assertNotNull(saved);
        assertEquals("alice@example.com", saved.getEmail());
        assertEquals("encodedPassword123", saved.getPassword());
    }

    @Test
    void getUserById_found() {
        User user = new User();
        user.setId(5L);
        user.setName("Demo");

        when(userDao.findById(5L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(5L);

        assertEquals("Demo", result.getName());
    }

    @Test
    void getUserById_notFound() {
        when(userDao.findById(100L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(100L));
    }
}

