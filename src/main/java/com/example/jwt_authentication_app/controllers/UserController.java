package com.example.jwt_authentication_app.controllers;

import com.example.jwt_authentication_app.entities.User;
import com.example.jwt_authentication_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) auth.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

}
