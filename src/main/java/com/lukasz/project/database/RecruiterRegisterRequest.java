package com.lukasz.project.database;

import com.lukasz.project.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecruiterRegisterRequest {

    @NotNull
    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull
    @NotEmpty(message = "lastname should not be empty")
    private String surname;

    @Email
    @NotNull
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotNull
    @NotEmpty(message = "username should not be empty")
    private String username;

    @NotNull
    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotNull
    @NotEmpty(message = "company id should not be empty")
    private Company company;

}
