package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@With
@Data
@DiscriminatorValue(value = "registered_user")
public class RegisteredUser extends User {

    @ManyToMany
    @JoinTable(
            name = "favorite_offers",
            joinColumns = @JoinColumn(name = "registered_user_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private Set<Offer> favoriteOffers;

    @Override
    public RegisteredUser withId(Integer id) {
        setId(id);
        return this;
    }

    @Override
    public RegisteredUser withIdentifier(String identifier) {
        setIdentifier(identifier);
        return this;
    }

    @Override
    public RegisteredUser withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public RegisteredUser withSurname(String surname) {
        setSurname(surname);
        return this;
    }

    @Override
    public RegisteredUser withPesel(String pesel) {
        setPesel(pesel);
        return this;
    }

    @Override
    public RegisteredUser withCountry(String country) {
        setCountry(country);
        return this;
    }

    @Override
    public RegisteredUser withUsername(String username) {
        setUsername(username);
        return this;
    }

    @Override
    public RegisteredUser withEmail(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public RegisteredUser withPassword(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public RegisteredUser withCreationDate(OffsetDateTime creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    @Override
    public RegisteredUser withRole(Role role) {
        setRole(role);
        return this;
    }
}
