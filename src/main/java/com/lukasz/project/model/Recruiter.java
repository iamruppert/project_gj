package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@DiscriminatorValue(value = "recruiter")
public class Recruiter extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.EAGER)
    private Set<Offer> createdOffers;
}
