package com.example.jwt_authentication_app.services;

import com.example.jwt_authentication_app.dto.AuthenticateUserDto;
import com.example.jwt_authentication_app.dto.RegisterUserDto;
import com.example.jwt_authentication_app.entities.Role;
import com.example.jwt_authentication_app.entities.User;
import com.example.jwt_authentication_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterUserDto registerUserDto)
    {
        User user = User.builder()
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(AuthenticateUserDto authenticateUserDto)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateUserDto.getEmail(),
                authenticateUserDto.getPassword()));

        return userRepository.findByEmail(authenticateUserDto.getEmail()).orElseThrow();
    }
}
