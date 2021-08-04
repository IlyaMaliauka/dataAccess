package com.epam.training.service.impl;

import com.epam.training.model.User;
import com.epam.training.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.epam.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User getUserById(long userId) {
        log.info("Attempting to get user with id {}", userId);
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Attempting to find user with email {}", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("Attempting to get users with name {}", name);
        return userRepo.findByName(name);
    }

    @Override
    public User createUser(User user) {
        log.info("Attempting to create new user");
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        log.info("Attempting to update user with id {}", user.getId());
        userRepo.delete(user);
        return userRepo.save(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        log.info("Attempting to delete user with id {}", userId);
        userRepo.deleteById(userId);
        return true;
    }
}
