package com.lukasz.project.service;

import com.lukasz.project.model.Recruiter;

import java.util.List;

public interface RecruiterService {
    void createRecruiter(Recruiter recruiter);
    List<Recruiter> findAllRecruiter();
}
