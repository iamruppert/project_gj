package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.OffsetDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
@Entity
@Table(name = "recruiter")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Integer recruiterId;

    @Column(name = "recruiter_identifier")
    private String recruiterIdentifier;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.EAGER)
    private Set<Offer> createdOffers;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @PrePersist
    public void prePersist() {// Call the prePersist() method of the superclass (User)
        if (recruiterIdentifier == null) {
            recruiterIdentifier = generateRandomIdentifier();
        }
    }

    private String generateRandomIdentifier() {
        String entityName = "RECRUITER"; // Możesz dostosować nazwę encji
        String randomNumber = RandomStringUtils.randomNumeric(20); // Losowe 5 cyfr
        return entityName + "_" + randomNumber;
    }

}
