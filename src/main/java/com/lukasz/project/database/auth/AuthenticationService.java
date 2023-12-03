package com.lukasz.project.database.auth;

import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.RecruiterRepository;
import com.lukasz.project.repository.RegisteredUserRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load the user based on the common identifier (email or username)
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    public RegisteredUser register(RegisterRequest request) {
        try {
            RegisteredUser newUser = extractor.createActorFromRequest(request, RegisteredUser.class);
            newUser.setRole(REGISTERED_USER);

            // Use PasswordEncoder to hash the password
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));

            registeredUserRepository.save(newUser);
            return newUser;
        } catch (Exception e) {
            // Log the exception or handle it more gracefully
            throw new RuntimeException("Error during registration", e);
        }
    }
    public String registerAdmin(RegisterRequest request) {
        try {
            Admin newAdmin = extractor.createActorFromRequest(request, Admin.class);
            newAdmin.setRole(ADMIN);
            newAdmin.setPassword(passwordEncoder.encode(request.getPassword()));

            adminRepository.save(newAdmin);
            return "Admin registered successfully";
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
    public String login(String username, String password) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate and return a JWT token or other authentication response
            return "Login successful";
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

}
