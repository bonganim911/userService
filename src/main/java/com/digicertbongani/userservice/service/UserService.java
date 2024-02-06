package com.digicertbongani.userservice.service;

import com.digicertbongani.userservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createANewUser(User user);

    User getUser(Long id);

    User updateExistingUser(User user);
}
