package com.lukasz.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@DiscriminatorValue(value = "registered_user")
public class RegisteredUser extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "favorite_offers",
            joinColumns = @JoinColumn(name = "registered_user_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private Set<Offer> favoriteOffers;


}
