package com.lukasz.project.controller;

import com.lukasz.project.database.auth.AuthenticationService;
import com.lukasz.project.database.auth.RegisterRequest;
import com.lukasz.project.dto.CompanyRequest;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.service.AdminServiceImpl;
import com.lukasz.project.service.CompanyServiceImpl;
import com.lukasz.project.service.RecruiterServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    private final RecruiterServiceImpl recruiterService;

    private final CompanyServiceImpl companyService;

    private final AuthenticationService authenticationService;


    @GetMapping("/findRecruiters")
    public ResponseEntity<List<Recruiter>> findRecruiters() {
        List<Recruiter> allRecruiters = recruiterService.findAllRecruiter();
        return new ResponseEntity<>(allRecruiters, HttpStatus.OK);
    }

    @GetMapping("/findAdmins")
    public ResponseEntity<List<Admin>> findAdmins() {
        List<Admin> allAdmin = adminService.findAllAdmin();
        return new ResponseEntity<>(allAdmin, HttpStatus.OK);
    }

    @PostMapping("/createCompany")
    public ResponseEntity<String> createCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        companyService.create(companyRequest);
        return ResponseEntity.ok("Company created successfully");
    }

    @PostMapping("/createRecruiter")
    public ResponseEntity<String> createRecruiter(@RequestBody @Valid RegisterRequest recruiterRequest) {
        authenticationService.registerRecruiter(recruiterRequest);
        return ResponseEntity.ok("Recruiter created successfully");
    }


}

