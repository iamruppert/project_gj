package com.lukasz.project.controller;

import com.lukasz.project.model.Offer;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.service.OfferServiceImpl;
import com.lukasz.project.service.RegisteredUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/registeredUser")
@AllArgsConstructor
public class RegisteredUserController {

    private final OfferServiceImpl offerService;
    private final RegisteredUserServiceImpl userService;

    @PostMapping("/addToFavourite/{offerId}")
    public ResponseEntity<String> addOfferToFavourite(
            @PathVariable Integer offerId,
            @AuthenticationPrincipal UserDetails userDetails){
        userService.addOfferToFavourites(offerId, userDetails);
        return ResponseEntity.ok(String.format("Offer with id {%s} added to favourites successfully", offerId));
    }
}

