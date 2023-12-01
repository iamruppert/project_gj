package com.lukasz.project.database;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminRegisterRequest {
    @NotNull
    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull
    @NotEmpty(message = "lastname should not be empty")
    private String surname;

    @NotNull
    @NotEmpty(message = "username should not be empty")
    private String username;

    @Email
    @NotNull
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotNull
    @NotEmpty(message = "password should not be empty")
    private String password;

}
