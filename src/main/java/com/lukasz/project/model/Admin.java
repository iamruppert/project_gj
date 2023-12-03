package com.lukasz.project.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Data
@DiscriminatorValue(value = "admin")
public class Admin extends User {

    @Override
    public Admin withId(Integer id) {
        setId(id);
        return this;
    }

    @Override
    public Admin withIdentifier(String identifier) {
        setIdentifier(identifier);
        return this;
    }

    @Override
    public Admin withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public Admin withSurname(String surname) {
        setSurname(surname);
        return this;
    }

    @Override
    public Admin withPesel(String pesel) {
        setPesel(pesel);
        return this;
    }

    @Override
    public Admin withCountry(String country) {
        setCountry(country);
        return this;
    }

    @Override
    public Admin withUsername(String username) {
        setUsername(username);
        return this;
    }

    @Override
    public Admin withEmail(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public Admin withPassword(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public Admin withCreationDate(OffsetDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    @Override
    public Admin withRole(Role role) {
        setRole(role);
        return this;
    }
}
