package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.OffsetDateTime;

@Getter
@Setter
@With
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_identifier")
    private String adminIdentifier;

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
        if (adminIdentifier == null) {
            adminIdentifier = generateRandomIdentifier();
        }
    }

    private String generateRandomIdentifier() {
        String entityName = "ADMIN"; // Możesz dostosować nazwę encji
        String randomNumber = RandomStringUtils.randomNumeric(20); // Losowe 5 cyfr
        return entityName + "_" + randomNumber;
    }
}
