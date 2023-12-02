package com.lukasz.project.service;

import com.lukasz.project.model.Company;
import com.lukasz.project.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void create(Company company){
        companyRepository.save(company);
    }
    public void delete(Integer id){
        companyRepository.deleteById(id);
    }
}
