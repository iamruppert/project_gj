package com.lukasz.project.service;

import com.lukasz.project.dto.OfferRequest;
import com.lukasz.project.model.Offer;

import java.util.List;

public interface OfferService {

    void createOffer(OfferRequest offerRequest);

    void deleteOffer(Integer id);

    void updateOffer(Integer id, OfferRequest offerRequest);

    List<Offer> getAllOffers();
}
