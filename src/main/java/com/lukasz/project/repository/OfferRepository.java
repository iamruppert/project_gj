package com.lukasz.project.repository;

import com.lukasz.project.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Page<Offer> findByNameContaining(String name, Pageable pageable);
}
