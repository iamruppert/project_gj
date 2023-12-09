package com.lukasz.project.controller;

import com.lukasz.project.dto.OfferRequest;
import com.lukasz.project.model.Offer;
import com.lukasz.project.service.OfferServiceImpl;
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

    private final OfferServiceImpl offerService;

    @PostMapping("/addOffer")
    public ResponseEntity<String> createOffer(@RequestBody @Valid OfferRequest offer) {
        offerService.createOffer(offer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Offer created successfully");
    }
    @DeleteMapping("/deleteOffer/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Offer with id {%s} removed successfully", id));
    }
    @PostMapping("/updateOffer/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody @Valid OfferRequest offerRequest) {
        offerService.updateOffer(id, offerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Offer with id {%s} updated successfully", id));
    }
    @GetMapping("/listAllOffers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> allOffers = offerService.getAllOffers();
        return new ResponseEntity<>(allOffers, HttpStatus.OK);
    }

}
