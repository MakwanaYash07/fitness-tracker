package com.fitness.tracker.service.impl;

import com.fitness.tracker.dao.UserDao;
import com.fitness.tracker.exception.ResourceNotFoundException;
import com.fitness.tracker.model.User;
import com.fitness.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User createUser(User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + user.getEmail());
        }
        // Encode the password before persisting
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        return userDao.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());

        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        if (userDetails.getRole() != null && !userDetails.getRole().isBlank()) {
            user.setRole(userDetails.getRole());
        }

        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userDao.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }
}