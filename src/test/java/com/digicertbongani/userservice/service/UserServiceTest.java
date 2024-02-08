package com.digicertbongani.userservice.service;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.repository.UserRepository;
import com.digicertbongani.userservice.repository.UserServiceImpl;
import com.digicertbongani.userservice.util.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    User commonUser;

    @BeforeEach
    public void setup() {
        commonUser = UserBuilder.builder()
                .id(1L)
                .firstName("Eric")
                .lastName("Thomas")
                .email("erict@user.com")
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(commonUser);
        mockUsers.add(UserBuilder.builder()
                .id(2L).firstName("Stephen")
                .lastName("Mitchel")
                .email("stephenm@user.com")
                .build());
        mockUsers.add(UserBuilder.builder()
                .id(3L).firstName("Lucky")
                .lastName("Shepard")
                .email("luckys@user.com")
                .build());

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertEquals(users.size(), mockUsers.size());
        assertFalse(users.isEmpty());
    }

    @Test
    public void shouldReturnAUsersGivenUserId() {

        when(userRepository.findById(commonUser.getId())).thenReturn(Optional.of(commonUser));

        Optional<User> expectedUser = userService.getUser(commonUser.getId());

        assertEquals(expectedUser.get().getId(), commonUser.getId());
    }

    @Test
    public void shouldReturnNullGivenUserIdDoesNotExist() {
        long userIdDoesNotExist = 12L;

        when(userRepository.findById(userIdDoesNotExist)).thenReturn(Optional.empty());

        Optional<User> expectedUser = userService.getUser(userIdDoesNotExist);

        assertTrue(expectedUser.isEmpty());
    }

    @Test
    public void shouldCreatNewUserAndReturnThatUser() {
        when(userRepository.save(commonUser)).thenReturn(commonUser);

        User newUser = userService.createUser(commonUser);

        assertEquals(newUser.getId(), commonUser.getId());

    }

    @Test
    public void shouldDeleteUserGivenThatUserExist() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(commonUser));
        boolean result = userService.deleteUser(userId);

        assertTrue(result);
        verify(userRepository).delete(commonUser);
    }

    @Test
    public void shouldNotBeAbleToDeleteUserGivenThatUserDoesNotExist() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        boolean result = userService.deleteUser(userId);

        assertFalse(result);
    }

    @Test
    public void shouldReturnUpdatedUserGivenUserIdAndUserObject() {
        long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(commonUser)).thenReturn(commonUser);

        User updatedUser = userService.updateExistingUser(userId, commonUser);

        assertEquals(updatedUser.getId(), commonUser.getId());
    }

    @Test
    public void shouldNotUpdatedUserGivenUserDoesNotExist() {
        long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        User updatedUser = userService.updateExistingUser(userId, commonUser);

        assertNull(updatedUser);
    }
}
