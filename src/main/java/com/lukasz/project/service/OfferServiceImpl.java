package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.OfferRequest;
import com.lukasz.project.model.Offer;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.OfferRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final Extractor extractor;

    @Override
    public void createOffer(OfferRequest offerRequest) {
        try {
            Offer offer = extractor.createOfferFromRequest(offerRequest);
            Optional<User> byEmail = userRepository.findByEmail(offerRequest.getEmail());
            byEmail.ifPresent(user -> offer.setRecruiter((Recruiter) user));
            offerRepository.save(offer);
        } catch (Exception e) {
            throw new RuntimeException("Error during creating offer", e);
        }

    }

    @Override
    public void deleteOffer(Integer id) {
        try {
            offerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error during deleting offer", e);
        }
    }

    @Override
    public void updateOffer(Integer id, OfferRequest offerRequest) {
        try {
            Optional<Offer> offerToUpdate = offerRepository.findById(id);
            if (offerToUpdate.isPresent()) {
                Offer offer = offerToUpdate.get();
                if (offerRequest.getName() != null) {
                    offer.setName(offerRequest.getName());
                }
                if (offerRequest.getPosition() != null) {
                    offer.setPosition(offerRequest.getPosition());
                }
                if (offerRequest.getKeywords() != null) {
                    offer.setKeywords(offerRequest.getKeywords());
                }
                if (offerRequest.getSalary() != null) {
                    offer.setSalary(new BigDecimal(offerRequest.getSalary()));
                }
                if (offerRequest.getCurrency() != null) {
                    offer.setCurrency(offerRequest.getCurrency());
                }
                offerRepository.save(offer);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during updating offer", e);
        }

    }

    @Override
    public List<Offer> getAllOffers() {
        try {
            return offerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching offers", e);
        }
    }
}
