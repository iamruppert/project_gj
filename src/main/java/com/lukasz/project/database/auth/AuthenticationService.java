package com.lukasz.project.database.auth;

import com.lukasz.project.database.config.JwtService;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.RecruiterRepository;
import com.lukasz.project.repository.RegisteredUserRepository;
import com.lukasz.project.repository.UserRepository;
import com.lukasz.project.token.Token;
import com.lukasz.project.token.TokenRepository;
import com.lukasz.project.token.TokenType;
import com.lukasz.project.validator.MyValidationException;
import com.lukasz.project.validator.ObjectValidatorImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    private final TokenRepository tokenRepository;
    private final ObjectValidatorImpl<RegisterRequest> validator;


    public AuthenticationResponse register(RegisterRequest request) {
        try {
            RegisteredUser user = extractor.createActorFromRequest(request, RegisteredUser.class);
            user.setRole(REGISTERED_USER);
            RegisteredUser save = userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(save, jwtToken);
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
            Admin save = userRepository.save(admin);
            var jwtToken = jwtService.generateToken(admin);
            saveUserToken(save, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error during admin registration", e);
        }
    }

    public AuthenticationResponse registerRecruiter(RegisterRequest request) {
        Set<String> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new MyValidationException(violations);
            }

            Recruiter newRecruiter = extractor.createActorFromRequest(request, Recruiter.class);
            newRecruiter.setRole(RECRUITER);
            Recruiter save = userRepository.save(newRecruiter);
            var jwtToken = jwtService.generateToken(newRecruiter);
            saveUserToken(save, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
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
        revokeAllUsersTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUsersTokens(User user) {
        var validUsersTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUsersTokens.isEmpty()) {
            return;
        }
        validUsersTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUsersTokens);
    }


    private void saveUserToken(User save, String jwtToken) {
        var token = Token.builder()
                .user(save)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
