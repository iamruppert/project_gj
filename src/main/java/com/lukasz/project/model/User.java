package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@With
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "identifier", unique = true)
    private String identifier;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "country")
    private String country;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void prePersist() {
        if (identifier == null) {
            identifier = generateRandomIdentifier();
        }
    }

    private String generateRandomIdentifier() {
        String entityName;
        String randomNumber;
        if (role.toString().equals("REGISTERED_USER")) {
            entityName = "REGISTERED_USER";
            randomNumber = RandomStringUtils.randomNumeric(20);
        } else if (role.toString().equals("ADMIN")) {
            entityName = "ADMIN";
            randomNumber = RandomStringUtils.randomNumeric(20);
        } else {
            entityName = "RECRUITER";
            randomNumber = RandomStringUtils.randomNumeric(20);

        }
        return entityName + "_" + randomNumber;
    }
}
