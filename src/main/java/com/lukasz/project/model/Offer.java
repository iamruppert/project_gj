package com.lukasz.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "offer")
@With
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private long offerId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "keywords")
    @ElementCollection
    private Set<String> keywords;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    private Set<User> favorites;
}

