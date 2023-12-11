package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.dto.OfferRequest;
import com.lukasz.project.model.Offer;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.OfferRepository;
import com.lukasz.project.repository.UserRepository;
import com.lukasz.project.validator.MyValidationException;
import com.lukasz.project.validator.ObjectValidatorImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final Extractor extractor;
    private final ObjectValidatorImpl<OfferRequest> validator;

    @Override
    public void createOffer(OfferRequest offerRequest) {
        Set<String> violations = validator.validate(offerRequest);
        if (!violations.isEmpty()) {
            throw new MyValidationException(violations);
        }

        Offer offer = extractor.createOfferFromRequest(offerRequest);
        User user = userRepository
                .findByEmail(offerRequest.getEmail())
                .orElseThrow(() -> new RuntimeException(String.format("User with email: [%s] not found", offerRequest.getEmail())));
        offer.setRecruiter((Recruiter) user);
        offerRepository.save(offer);
    }


    @Override
    public void deleteOffer(Integer id) {

        if(offerRepository.findById(id).isEmpty()){
            throw new RuntimeException(String.format("Offer with id: {%s} does not exist",id));
        }
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(Integer id, OfferRequest offerRequest) {

            Set<String> violations = validator.validate(offerRequest);
            if (!violations.isEmpty()) {
                throw new MyValidationException(violations);
            }

            Optional<Offer> offerToUpdate = offerRepository.findById(id);
            if (offerToUpdate.isPresent()) {
                Offer offer = offerToUpdate.get();

                offer.setName(offerRequest.getName());
                offer.setPosition(offerRequest.getPosition());
                offer.setKeywords(offerRequest.getKeywords());
                offer.setSalary(new BigDecimal(offerRequest.getSalary()));
                offer.setCurrency(offerRequest.getCurrency());

                offerRepository.save(offer);
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

    @Override
    public Page<Offer> getAllOffers(int page, int size, String sortBy) {
        Sort sort;
        if ("salary_asc".equals(sortBy)) {
            sort = Sort.by("salary").ascending();
        } else if ("salary_desc".equals(sortBy)) {
            sort = Sort.by("salary").descending();
        } else {
            sort = Sort.by(sortBy);
        }

        try {
            return offerRepository.findAll(PageRequest.of(page, size, sort));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching offers", e);
        }
    }
}
