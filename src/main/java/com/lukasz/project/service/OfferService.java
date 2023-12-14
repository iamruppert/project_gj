package com.lukasz.project.service;

import com.lukasz.project.dto.OfferRequest;
import com.lukasz.project.model.Offer;
import org.springframework.data.domain.Page;

public interface OfferService {

    void createOffer(OfferRequest offerRequest);

    void deleteOffer(Integer id);

    void updateOffer(Integer id, OfferRequest offerRequest);


    Page<Offer> getAllOffers(int page, int size, String sortBy);

    Page<Offer> searchOffers(int page, int size, String sortBy, String searchPhrase);
}
