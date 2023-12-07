package com.lukasz.project.controller;

import com.lukasz.project.database.auth.AuthenticationService;
import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.auth.RegisterRequest;
import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Company;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.service.AdminService;
import com.lukasz.project.service.CompanyService;
import com.lukasz.project.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final RecruiterService recruiterService;

    private final CompanyService companyService;

    private final AuthenticationService authenticationService;


    @GetMapping("/findRecruiters")
    public ResponseEntity<List<Recruiter>> findRecruiters() {
        try {
            List<Recruiter> allRecruiters = recruiterService.findAllRecruiter();
            System.out.println(Arrays.asList(allRecruiters));
            return new ResponseEntity<>(allRecruiters, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAdmins")
    public ResponseEntity<List<Admin>> findAdmins() {
        try {
            List<Admin> allAdmin = adminService.findAllAdmin();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCompany")
    public ResponseEntity<String> createCompany(@Valid @RequestBody CompanyRequest companyRequest){
        try{
            companyService.create(companyRequest);
            return ResponseEntity.ok("Company created successfully");
        }
        catch(Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createRecruiter")
    public ResponseEntity<String> createRecruiter(@Valid @RequestBody RegisterRequest recruiterRequest){
        try{
            authenticationService.registerRecruiter(recruiterRequest);
            return ResponseEntity.ok("Recruiter created successfully");
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}

