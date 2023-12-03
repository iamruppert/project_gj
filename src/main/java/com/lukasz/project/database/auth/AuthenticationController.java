package com.lukasz.project.database.auth;

import com.lukasz.project.database.request.AuthenticationRequest;
import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.RegisteredUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService, UserDetailsService userDetailsService) {
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            // Convert RegisterRequest to RegisteredUser entity
            RegisteredUser newUser = authenticationService.register(registerRequest);

            // Load the newly registered user using UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            // Log the exception or handle it more gracefully
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        try {
            authenticationService.registerAdmin(registerRequest);

            return ResponseEntity.ok("Admin registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Admin registration failed: " + e.getMessage());
        }
    }
    @PostMapping("/register-recruiter")
    public ResponseEntity<String> registerRecruiter(@RequestBody RegisterRequest registerRequest) {
        try {
            authenticationService.registerRecruiter(registerRequest);

            return ResponseEntity.ok("Recruiter registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Recruiter registration failed: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationService.login(authenticationRequest.getEmail(), authenticationRequest.getPassword());

            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

}
