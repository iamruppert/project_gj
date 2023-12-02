package com.lukasz.project.controller;

import com.lukasz.project.model.Offer;
import com.lukasz.project.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter")
@AllArgsConstructor
public class RecruiterController {

    private final OfferService offerService;

    @PostMapping("/addOffer")
    public ResponseEntity<String> createOffer(@RequestBody Offer offer){
        try{
            offerService.createOffer(offer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Offer created successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create offer");
        }
    }
    @PostMapping("/deleteOffer")
    public ResponseEntity<String> deleteOffer(@RequestBody Offer offer){
        try{
            offerService.deleteOffer(offer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Offer removed successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove offer");
        }
    }
}
