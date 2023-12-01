package com.lukasz.project.controller;

import com.lukasz.project.company.CompanyService;
import com.lukasz.project.entity.Admin;
import com.lukasz.project.entity.Company;
import com.lukasz.project.entity.Recruiter;
import com.lukasz.project.recruiter.RecruiterRegisterRequest;
import com.lukasz.project.recruiter.RecruiterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final RecruiterService recruiterService;

    private final CompanyService companyService;


    @PostMapping("/createRecruiter")
    public ResponseEntity<String> createRecruiter(@RequestBody RecruiterRegisterRequest request) {
        try {
            Company company = new Company();  // You might need to fetch the actual Company entity from the database based on companyId
            company.setCompanyId(request.getCompany().getCompanyId());

            var recruiter = Recruiter.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .company(company)
                    .build();

            recruiterService.createRecruiter(recruiter);
            return ResponseEntity.status(HttpStatus.CREATED).body("Recruiter created successfully");
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
    public ResponseEntity<String> createAdmin(@RequestBody AdminRegisterRequest request) {
        try {

            var admin = Admin.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build();

            adminService.createAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin created successfully");
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
    ResponseEntity<String> createCompany(@RequestBody Company company) {
        try {
            companyService.create(company);
            return ResponseEntity.ok("Company created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create company");
        }
    }

    @PostMapping("/deleteCompany")
    ResponseEntity<String> deleteCompany(@RequestBody Long id) {
        try {
            companyService.delete(id);
            return ResponseEntity.ok("Company deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete company");
        }
    }


}

