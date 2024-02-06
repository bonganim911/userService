package com.digicertbongani.userservice.service;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.repository.UserRepository;
import com.digicertbongani.userservice.repository.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllUsers(){

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(1L, "Eric","Thomas","erict@user.com"));
        mockUsers.add(new User(2L, "Stephen","Mitchel","stephenm@user.com"));
        mockUsers.add(new User(3L, "Lucky","Shepard","luckys@user.com"));

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertEquals(users.size(),mockUsers.size());


        assertTrue(!users.isEmpty());
    }

    @Test
    public void shouldReturnAUsersGivenUserId(){

        User actualUser = new User(1L, "Eric","Thomas","erict@user.com");

        when(userRepository.findById(actualUser.getId())).thenReturn(Optional.of(actualUser));

        Optional<User> expectedUser = userService.getUser(actualUser.getId());

        assertEquals(expectedUser.get().getId(),actualUser.getId());
    }
}
