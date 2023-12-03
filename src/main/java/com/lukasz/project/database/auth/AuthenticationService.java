package com.lukasz.project.database.auth;

import com.lukasz.project.database.config.JwtService;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.RecruiterRepository;
import com.lukasz.project.repository.RegisteredUserRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.lukasz.project.model.Role.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final AdminRepository adminRepository;
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Extractor extractor;
    private final JwtService jwtService;


    public AuthenticationResponse register(RegisterRequest request) {
        try {
            RegisteredUser user = extractor.createActorFromRequest(request, RegisteredUser.class);
            user.setRole(REGISTERED_USER);
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error during registration", e);
        }
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        try {
            Admin admin = extractor.createActorFromRequest(request, Admin.class);
            admin.setRole(ADMIN);
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(admin);
            var jwtToken = jwtService.generateToken(admin);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error during admin registration", e);
        }
    }
    public String registerRecruiter(RegisterRequest request) {
        try {
            Recruiter newRecruiter = extractor.createActorFromRequest(request, Recruiter.class);
            newRecruiter.setRole(RECRUITER);
            newRecruiter.setPassword(passwordEncoder.encode(request.getPassword()));

            recruiterRepository.save(newRecruiter);
            return "Recruiter registered successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error during recruiter registration", e);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
