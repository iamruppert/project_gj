package com.lukasz.project.controller;

import com.lukasz.project.database.auth.Extractor;
import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Company;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.Role;
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
        try {
            Recruiter recruiter = extractor.createActorFromRequest(request, Recruiter.class);
            recruiter.setRole(Role.RECRUITER);
                recruiterService.createRecruiter(recruiter);
                return ResponseEntity.status(HttpStatus.OK).body("Recruiter created successfully");

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
        try {

            Admin admin = extractor.createActorFromRequest(request, Admin.class);
            admin.setRole(Role.ADMIN);
                adminService.createAdmin(admin);
                return ResponseEntity.status(HttpStatus.OK).body("Admin created successfully");
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

