package com.lukasz.project.controller;

import com.lukasz.project.database.request.OfferRequest;
import com.lukasz.project.model.Offer;
import com.lukasz.project.service.OfferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
@AllArgsConstructor
public class RecruiterController {

    private final OfferService offerService;

    @PostMapping("/addOffer")
    public ResponseEntity<String> createOffer(@Valid @RequestBody OfferRequest offer) {
        try{
            offerService.createOffer(offer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Offer created successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create offer");
        }
    }
    @DeleteMapping("/deleteOffer/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Integer id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Offer removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove offer");
        }
    }
    @PostMapping("/updateOffer/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody OfferRequest offerRequest) {
        try {
            offerService.updateOffer(id, offerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Offer updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove offer");
        }
    }
    @GetMapping("/listAllOffers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        try {
            List<Offer> allOffers = offerService.getAllOffers();
            return new ResponseEntity<>(allOffers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
