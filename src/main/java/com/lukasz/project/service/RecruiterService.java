package com.lukasz.project.service;

import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.Role;
import com.lukasz.project.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public void createRecruiter(Recruiter recruiter) {
        // Dodajemy logikę sprawdzania, czy rekruter o podanym emailu już istnieje
        if (recruiterRepository.existsByEmail(recruiter.getEmail())) {
            throw new RuntimeException("Recruiter with this email already exists");
        }

        recruiter.setCreatedOffers(null);


        recruiterRepository.save(recruiter);
    }

    public List<Recruiter> findAllRecruiter(){
        return recruiterRepository.findAllRecruiters();
    }

}
