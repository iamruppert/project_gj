package com.lukasz.project.validator;


import java.util.Set;

public interface ObjectValidator<T> {
    Set<String> validate(T objectToValidate);
}
