package com.lukasz.project.controller;

import com.lukasz.project.service.RegisteredUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registeredUser")
@AllArgsConstructor
public class RegisteredUserController {

    private final RegisteredUserServiceImpl userService;

    @PostMapping("/addToFavourite/{offerId}")
    public ResponseEntity<String> addOfferToFavourite(
            @PathVariable Integer offerId,
            @AuthenticationPrincipal UserDetails userDetails){
        userService.addOfferToFavourites(offerId, userDetails);
        return ResponseEntity.ok(String.format("Offer with id {%s} added to favourites successfully", offerId));
    }
}

