package com.lukasz.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private long offerId;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "keywords")
    @ElementCollection
    private Set<String> keywords;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "currency")
    @Enumerated
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    private Set<User> favorites;
}

