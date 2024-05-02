package com.example.jwt_authentication_app.controllers;

import com.example.jwt_authentication_app.entities.User;
import com.example.jwt_authentication_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    Logger LOG = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        System.out.println("Hello");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) auth.getPrincipal();
        LOG.info("Username: " + currentUser.getUsername());
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAllUsers();
        users.forEach(user -> {LOG.info("Username: " + user.getEmail());});
        return ResponseEntity.ok(users);
    }

}
