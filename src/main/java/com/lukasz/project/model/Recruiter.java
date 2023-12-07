package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@DiscriminatorValue(value = "recruiter")
public class Recruiter extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.LAZY)
    private Set<Offer> createdOffers;
}
