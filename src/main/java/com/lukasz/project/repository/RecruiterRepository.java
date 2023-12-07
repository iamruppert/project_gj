package com.lukasz.project.repository;

import com.lukasz.project.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
    Optional<Recruiter> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = 'RECRUITER'")
    List<Recruiter> findAllRecruiters();
}
