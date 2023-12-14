package com.lukasz.project.service;

import com.lukasz.project.dto.OfferRequest;
import com.lukasz.project.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {

    void createOffer(OfferRequest offerRequest);

    void deleteOffer(Integer id);

    void updateOffer(Integer id, OfferRequest offerRequest);

    List<Offer> getAllOffers();

    Page<Offer> getAllOffers(int page, int size, String sortBy);

    Page<Offer> searchOffers(int page, int size, String sortBy, String searchPhrase);
}
