package com.example.jwt_authentication_app.dto;

import com.example.jwt_authentication_app.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;
}
