package com.lukasz.project.service;


import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.repository.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private RegisteredUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void updateUser(RegisteredUser user) {
        userRepository.save(user);
    }

    @Override
    public RegisteredUser findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
