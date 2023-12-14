package com.lukasz.project.service;

import com.lukasz.project.model.Recruiter;
import com.lukasz.project.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;

    @Override
    public void createRecruiter(Recruiter recruiter) {
        if (recruiterRepository.existsByEmail(recruiter.getEmail())) {
            throw new RuntimeException("Recruiter with this email already exists");
        }
        recruiter.setCreatedOffers(null);
        recruiterRepository.save(recruiter);
    }

    @Override
    public List<Recruiter> findAllRecruiter() {
        try {
            return recruiterRepository.findAllRecruiters();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching recruiters", e);
        }
    }

}
