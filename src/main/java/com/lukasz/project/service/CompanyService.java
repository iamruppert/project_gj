package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.model.Company;
import com.lukasz.project.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final Extractor extractor;

    public void create(CompanyRequest companyRequest){
        Company company = extractor.createCompanyFromRequest(companyRequest);
        companyRepository.save(company);
    }
    public void delete(Integer id){
        companyRepository.deleteById(id);
    }
}
