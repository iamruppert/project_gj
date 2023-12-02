package com.lukasz.project.database.auth;

import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.*;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.RecruiterRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Extractor extractor;

    public String register(RegisterRequest request) {

        try {
            switch (request.getRole()) {
                case USER: {
                    User newUser = extractor.createActorFromRequest(request, User.class);
                    userRepository.save(newUser);
                    break;
                }
//                // TODO: 12/2/2023 czy admn i recrui
//                case ADMIN: {
//                    Admin newAdmin = extractor.createFromRequest(request, Admin.class);
//                    adminRepository.save(newAdmin);
//                    break;
//                }
//                case RECRUITER: {
//                    Recruiter newRecruiter = extractor.createFromRequest(request, Recruiter.class);
//                    recruiterRepository.save(newRecruiter);
//                    break;
//                }
                default:
                    throw new IllegalArgumentException("Invalid role: " + request.getRole());
            }
            return "Zarejestrowano pomyślnie";
        } catch (Exception e) {
            e.printStackTrace(); // Możesz logować wyjątek dla celów diagnostycznych
            return "Wystąpił błąd podczas rejestracji: " + e.getMessage();
        }
    }

}
