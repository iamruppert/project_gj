package com.lukasz.project.repository;

import com.lukasz.project.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    Optional<RegisteredUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
