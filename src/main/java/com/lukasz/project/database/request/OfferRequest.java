package com.lukasz.project.database.request;

import com.lukasz.project.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {

    private String name;

    private String position;

    private Set<String> keywords;

    private String salary;

    private Currency currency;

    private String email;

}
