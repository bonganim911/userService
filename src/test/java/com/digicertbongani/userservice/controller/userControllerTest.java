package com.digicertbongani.userservice.controller;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class userControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(1L, "Eric","Thomas","erict@user.com"));
        mockUsers.add(new User(2L, "Stephen","Mitchel","stephenm@user.com"));
        mockUsers.add(new User(3L, "Lucky","Shepard","luckys@user.com"));

        when(userService.getAllUsers()).thenReturn(mockUsers);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void shouldReturnUserGivenId() throws Exception {
        User mockUser = new User(1L, "Eric","Thomas","erict@user.com");
        when(userService.getUser(mockUser.getId())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Eric")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("erict@user.com")));

    }

    @Test
    public void shouldReturnUserNotFoundGivenUserIdDoesExist() throws Exception {

        when(userService.getUser(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/0"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldReturnUpdatedUserGivenUserId() throws Exception {
        User updatedUser = new User(1L, "Tommy", "Hilfiger", "tommyh@user.com");

        when(userService.updateExistingUser(1L, updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedUser)))
                .andExpect(status().isOk());

    }

    @Test
    public void testShouldReturnNotFoundGivenTheUserDoesNotExist() throws Exception {
        User updatedUser = new User(2L, "Jane","Doe","jane@example.com");

        Mockito.when(userService.updateExistingUser(2L, updatedUser)).thenReturn(null);

        mockMvc.perform(put("/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedUser)))
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
