package com.lukasz.project.validator;

import lombok.Getter;

import java.util.Set;

@Getter
public class MyValidationException extends RuntimeException{
    private final Set<String> validationErrors;

    public MyValidationException(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    public Set<String> getViolations() {
        return validationErrors;
    }
}
