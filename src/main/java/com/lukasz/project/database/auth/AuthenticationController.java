package com.lukasz.project.database.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.registerAdmin(registerRequest));
    }
//    @PostMapping("/register-recruiter")
//    public ResponseEntity<String> registerRecruiter(@RequestBody RegisterRequest registerRequest) {
//        try {
//            authenticationService.registerRecruiter(registerRequest);
//
//            return ResponseEntity.ok("Recruiter registered successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Recruiter registration failed: " + e.getMessage());
//        }
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
