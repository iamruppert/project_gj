package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.OfferRequest;
import com.lukasz.project.model.Offer;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.OfferRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final Extractor extractor;
    public void createOffer(OfferRequest offerRequest){
        Offer offer = extractor.createOfferFromRequest(offerRequest);
        Optional<User> byEmail = userRepository.findByEmail(offerRequest.getEmail());
        byEmail.ifPresent(user -> offer.setRecruiter((Recruiter) user));
        offerRepository.save(offer);
    }
    public void deleteOffer(Integer id){
        offerRepository.deleteById(id);
    }
}
