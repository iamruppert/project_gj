package com.lukasz.project.database.auth;

import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.RecruiterRepository;
import com.lukasz.project.repository.RegisteredUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.lukasz.project.model.Role.REGISTERED_USER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RegisteredUserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RecruiterRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Extractor extractor;

    public String register(RegisterRequest request) {

        try {
            RegisteredUser newUser = extractor.createActorFromRequest(request, RegisteredUser.class);
            newUser.setRole(REGISTERED_USER);
            userRepository.save(newUser);
            return "Zarejestrowano pomyślnie";
//                // TODO: 12/2/2023 czy admin i recruiter
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
        } catch (Exception e) {
            e.printStackTrace(); // Możesz logować wyjątek dla celów diagnostycznych
            return "Wystąpił błąd podczas rejestracji: " + e.getMessage();
        }
    }

}
