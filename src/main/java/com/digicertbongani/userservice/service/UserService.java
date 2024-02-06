package com.digicertbongani.userservice.service;

import com.digicertbongani.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    Optional<User> getUser(Long id);

    User updateExistingUser(long userId, User user);

    boolean deleteUser(long id);
}
