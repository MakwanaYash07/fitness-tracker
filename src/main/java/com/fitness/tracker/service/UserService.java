package com.fitness.tracker.service;

import com.fitness.tracker.model.User;

import java.util.List;

/**
 * @author Yash
 */
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    User findByEmail(String email);
}
