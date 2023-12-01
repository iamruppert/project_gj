package com.lukasz.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "country")
    private String country;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @ManyToMany
    @JoinTable(
            name = "favorite_offers",
            joinColumns = @JoinColumn(name = "registered_user_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private Set<Offer> favoriteOffers;

    // TODO: 12/1/2023 role

}
