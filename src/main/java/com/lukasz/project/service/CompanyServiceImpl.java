package com.lukasz.project.service;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.model.Company;
import com.lukasz.project.repository.CompanyRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final Extractor extractor;

    @Override
    public void create(CompanyRequest companyRequest){
        try {
            Company company = extractor.createCompanyFromRequest(companyRequest);
            companyRepository.save(company);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating the company", e);
        }
    }
    @Override
    public void delete(Integer id){
        companyRepository.deleteById(id);
    }



}
