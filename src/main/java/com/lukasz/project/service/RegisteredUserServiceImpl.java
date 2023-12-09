package com.lukasz.project.service;


import com.lukasz.project.model.Offer;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.repository.OfferRepository;
import com.lukasz.project.repository.RegisteredUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private final RegisteredUserRepository userRepository;
    private final OfferRepository offerRepository;

    @Override
    public void updateUser(RegisteredUser user) {
        userRepository.save(user);
    }


    @Override
    public void addOfferToFavourites(Integer offerId, UserDetails userDetails)  {

        RegisteredUser user = userRepository
                .findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Offer with id {%s} not found", offerId)));

        user.getFavoriteOffers().add(offer);
        updateUser(user);

    }

}
