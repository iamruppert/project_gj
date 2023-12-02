package com.lukasz.project.repository;

import com.lukasz.project.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
    Optional<Recruiter> findByEmail(String email);
    boolean existsByEmail(String email);
}
