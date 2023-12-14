package com.lukasz.project.service;

import com.lukasz.project.model.RegisteredUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface RegisteredUserService {

    void updateUser(RegisteredUser user);

    void addOfferToFavourites(Integer offerId, UserDetails userDetails);
}
