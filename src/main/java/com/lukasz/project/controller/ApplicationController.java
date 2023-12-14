package com.lukasz.project.controller;

import com.lukasz.project.model.Offer;
import com.lukasz.project.service.OfferServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApplicationController {

    private final OfferServiceImpl offerService;

    @GetMapping("/listAllOffers")
    public ResponseEntity<Page<Offer>> getAllOffers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        // Przekazanie żądania do serwisu
        Page<Offer> allOffers = offerService.getAllOffers(page, size, sortBy);

        return new ResponseEntity<>(allOffers, HttpStatus.OK);
    }

    @GetMapping("/searchOffers")
    public ResponseEntity<Page<Offer>> searchOffers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam String searchPhrase) {

        // Przekazanie żądania do serwisu
        Page<Offer> searchedOffers = offerService.searchOffers(page, size, sortBy, searchPhrase);

        return new ResponseEntity<>(searchedOffers, HttpStatus.OK);
    }
}
