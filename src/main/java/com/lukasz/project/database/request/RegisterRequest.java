package com.lukasz.project.database.request;

import com.lukasz.project.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name can't be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname can't be blank")
    @NotNull(message = "Surname is required")
    private String surname;

    @NotBlank(message = "Pesel can't be blank")
    @NotNull(message = "Pesel is required")
    private String pesel;

    @NotBlank(message = "Country can't be blank")
    @NotNull(message = "Country is required")
    private String country;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email can't be blank")
    @NotNull(message = "Email is required")
    private String email;

    @NotBlank(message = "Username can't be blank")
    @NotNull(message = "Username is required")
    private String username;

    @NotBlank(message = "Password can't be blank")
    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}
