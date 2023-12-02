package com.lukasz.project.service;

import com.lukasz.project.model.Offer;
import com.lukasz.project.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public void createOffer(Offer offer){
        offerRepository.save(offer);
    }
    public void deleteOffer(Offer offer){
        offerRepository.delete(offer);
    }

}
