package com.example.jwt_authentication_app.controllers;

import com.example.jwt_authentication_app.dto.AuthenticateUserDto;
import com.example.jwt_authentication_app.dto.RegisterUserDto;
import com.example.jwt_authentication_app.entities.User;
import com.example.jwt_authentication_app.responses.AuthenticationResponse;
import com.example.jwt_authentication_app.services.AuthenticationService;
import com.example.jwt_authentication_app.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody RegisterUserDto registerUserDto)
    {
        User registeredUser = authenticationService.register(registerUserDto);

        LOG.info("User " + registeredUser.getEmail() + " registered!");
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateUserDto authenticateUserDto)
    {
        User authenticatedUser = authenticationService.authenticate(authenticateUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationResponse response = new AuthenticationResponse(jwtToken);

        LOG.info("User " + authenticatedUser.getEmail() + " authenticated!");
        return ResponseEntity.ok(response);
    }
}
