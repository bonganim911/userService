package com.digicertbongani.userservice.controller;

import com.digicertbongani.userservice.model.User;
import com.digicertbongani.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") Long userId){
        Optional<User> user = userService.getUser(userId);
        if(user.isPresent())
            return ResponseEntity.ok(user);

        return ResponseEntity.notFound().build();
    }
}
