package com.digicertbongani.userservice.repository;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateExistingUser(long userId, User user) {
        boolean userExist = userRepository.existsById(userId);
        if (userExist)
            return userRepository.save(user);
        return null;
    }

    @Override
    public boolean deleteUser(long id) {
        Optional<User> existingUser = getUser(id);
        if(existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return true;
        }
        return false;
    }
}
