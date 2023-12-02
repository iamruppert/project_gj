package com.lukasz.project.controller;

import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.RegisterRequest;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final RecruiterService recruiterService;

    private final CompanyService companyService;

    private final Extractor extractor;


    @PostMapping("/createRecruiter")
    public ResponseEntity<String> createRecruiter(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, construct an error message and return a bad request response
            StringBuilder errorMessage = new StringBuilder("Invalid request. Please fix the following issues:\n");
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        try {
            Recruiter recruiter;
            if (Objects.equals(request.getRole().toString(), "RECRUITER")) {
                recruiter = extractor.createActorFromRequest(request, Recruiter.class);
                recruiterService.createRecruiter(recruiter);
                return ResponseEntity.status(HttpStatus.OK).body("Recruiter created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Creating recruiter failed");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create recruiter");
        }
    }

    @GetMapping("/findRecruiters")
    public ResponseEntity<List<Recruiter>> findRecruiters() {
        try {
            List<Recruiter> allRecruiters = recruiterService.findAllRecruiter();
            return new ResponseEntity<>(allRecruiters, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createAdmin")
    public ResponseEntity<String> createAdmin(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, construct an error message and return a bad request response
            StringBuilder errorMessage = new StringBuilder("Invalid request. Please fix the following issues:\n");
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        try {
            Admin admin;
            if (Objects.equals(request.getRole().toString(), "ADMIN")) {
                admin = extractor.createActorFromRequest(request, Admin.class);
                adminService.createAdmin(admin);
                return ResponseEntity.status(HttpStatus.OK).body("Admin created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Creating admin failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create admin");
        }
    }


    @GetMapping("/findAdmins")
    public ResponseEntity<List<Admin>> findAdmins() {
        try {
            List<Admin> allAdmin = adminService.findAllAdmin();
            return new ResponseEntity<>(allAdmin, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createCompany")
    ResponseEntity<String> createCompany(@Valid @RequestBody CompanyRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, construct an error message and return a bad request response
            StringBuilder errorMessage = new StringBuilder("Invalid request. Please fix the following issues:\n");
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        try {
            Company company = extractor.createCompanyFromRequest(request);
            companyService.create(company);
            return ResponseEntity.ok("Company created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create company");
        }
    }

//    @PostMapping("/deleteCompany")
//    ResponseEntity<String> deleteCompany(@RequestBody Integer id) {
//        try {
//            companyService.delete(id);
//            return ResponseEntity.ok("Company deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete company");
//        }
//    }



}

