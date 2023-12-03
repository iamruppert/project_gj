package com.lukasz.project.controller;

import com.lukasz.project.model.Offer;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.service.OfferService;
import com.lukasz.project.service.RegisteredUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registeredUser")
@AllArgsConstructor
public class RegisteredUserController {

    private final OfferService offerService;
    private final RegisteredUserService userService;

    @PostMapping("/addToFavourite")
    public ResponseEntity<String> addOfferToFavourite(
            @RequestBody Offer offer,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            if (userDetails != null) {
                // Fetch the logged-in user
                RegisteredUser user = userService.findUserByEmail(userDetails.getUsername());

                // Add the offer to the user's favorite offers
                user.getFavoriteOffers().add(offer);

                // Save the updated user
                userService.updateUser(user);

                return ResponseEntity.ok("Offer added to favorites successfully");
            } else {
                return ResponseEntity.status(401).body("User not authenticated");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(500).body("Failed to add offer to favorites");
        }
    }
}
