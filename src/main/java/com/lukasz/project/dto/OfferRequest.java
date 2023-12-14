package com.lukasz.project.dto;

import com.lukasz.project.model.Currency;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {

    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "Position can't be blank")
    private String position;

    @NotNull(message = "Keywords can't be null")
    @NotEmpty(message = "Keywords can't be empty")
    private Set<String> keywords;

    @NotBlank(message = "Salary can't be blank")
    @Pattern(regexp = "\\d+", message = "Salary must be a valid number")
    private String salary;

    @NotNull(message = "Currency can't be null")
    private Currency currency;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;
}
