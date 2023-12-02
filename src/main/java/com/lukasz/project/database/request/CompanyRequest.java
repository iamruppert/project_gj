package com.lukasz.project.database.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {


    @NotBlank(message = "Name can't be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Website can't be blank")
    @NotNull(message = "Website is required")
    private String website;

    @NotBlank(message = "Address can't be blank")
    @NotNull(message = "Address is required")
    private String address;

}
