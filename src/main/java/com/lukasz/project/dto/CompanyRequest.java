package com.lukasz.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "Website can't be blank")
    private String website;

    @NotBlank(message = "Address can't be blank")
    private String address;

}
