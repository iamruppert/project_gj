package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.dto.CompanyRequest;
import com.lukasz.project.model.Company;
import com.lukasz.project.repository.CompanyRepository;
import com.lukasz.project.validator.MyValidationException;
import com.lukasz.project.validator.ObjectValidatorImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final Extractor extractor;
    private final ObjectValidatorImpl<CompanyRequest> validator;

    @Override
    public void create(CompanyRequest companyRequest){
        Set<String> violations = validator.validate(companyRequest);
        if (!violations.isEmpty()) {
            throw new MyValidationException(violations);
        }
            Company company = extractor.createCompanyFromRequest(companyRequest);
            companyRepository.save(company);
    }
    @Override
    public void delete(Integer id){
        companyRepository.deleteById(id);
    }



}
