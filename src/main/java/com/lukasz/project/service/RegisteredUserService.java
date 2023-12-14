package com.lukasz.project.service;

import com.lukasz.project.model.Offer;
import com.lukasz.project.model.RegisteredUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.AccessDeniedException;

public interface RegisteredUserService {

    void updateUser(RegisteredUser user);

    void addOfferToFavourites(Integer offerId, UserDetails userDetails);
}
