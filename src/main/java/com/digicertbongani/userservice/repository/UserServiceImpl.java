package com.digicertbongani.userservice.repository;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createANewUser(User user) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User updateExistingUser(User user) {
        return null;
    }
}
