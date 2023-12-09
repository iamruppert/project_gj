package com.lukasz.project.service;

import com.lukasz.project.database.request.CompanyRequest;

public interface CompanyService {

    void create(CompanyRequest companyRequest);
    void delete(Integer id);
}
