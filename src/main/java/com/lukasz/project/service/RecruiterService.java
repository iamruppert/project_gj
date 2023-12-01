package com.lukasz.project.service;

import com.lukasz.project.entity.Recruiter;
import com.lukasz.project.entity.Role;
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

        // TODO: 12/1/2023 role
        recruiter.setRole(Role.RECRUITER);

        recruiterRepository.save(recruiter);
    }

    public List<Recruiter> findAllRecruiter(){
        return recruiterRepository.findAll();
    }

}
