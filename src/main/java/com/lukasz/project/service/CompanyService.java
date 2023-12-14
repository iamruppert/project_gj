package com.lukasz.project.service;

import com.lukasz.project.dto.CompanyRequest;

public interface CompanyService {

    void create(CompanyRequest companyRequest);
    void delete(Integer id);
}
