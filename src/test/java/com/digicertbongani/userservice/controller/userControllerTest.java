package com.digicertbongani.userservice.controller;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.service.UserService;
import com.digicertbongani.userservice.util.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class userControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    User commonUser;
    @BeforeEach
    public void setup(){
        commonUser = UserBuilder.builder()
                .id(1L)
                .firstName("Eric")
                .lastName("Thomas")
                .email("erict@user.com")
                .build();
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
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

        when(userService.getAllUsers()).thenReturn(mockUsers);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void shouldReturnUserGivenId() throws Exception {
        when(userService.getUser(commonUser.getId())).thenReturn(Optional.of(commonUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.firstName", Matchers.is("Eric")))
                .andExpect(jsonPath("$.email", Matchers.is("erict@user.com")));

    }

    @Test
    public void shouldReturnUserNotFoundGivenUserIdDoesExist() throws Exception {

        when(userService.getUser(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/0"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testShouldCreateUser() throws Exception {

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(commonUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commonUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.firstName", Matchers.is("Eric")))
                .andExpect(jsonPath("$.email", Matchers.is("erict@user.com")));
    }

    @Test
    public void shouldReturnUpdatedUserGivenUserId() throws Exception {
        User updatedUser = UserBuilder.builder()
                .id(1L).firstName("Tommy")
                .lastName("Hilfiger")
                .email("tommyh@user.com")
                .build();


        when(userService.updateExistingUser(1L, updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedUser)))
                .andExpect(status().isOk());

    }

    @Test
    public void testShouldReturnNotFoundGivenTheUserDoesNotExist() throws Exception {
        when(userService.updateExistingUser(22L, commonUser)).thenReturn(null);

        mockMvc.perform(put("/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commonUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testShouldDeleteUserGivenUserId() throws Exception {
        when(userService.deleteUser(2L)).thenReturn(true);

        mockMvc.perform(delete("/users/2"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testShouldNotDeleteUserGivenUseIdDoesNotExist() throws Exception {
        long userId = 15L;


        Mockito.when(userService.deleteUser(userId)).thenReturn(false);


        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
