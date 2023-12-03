package com.lukasz.project.database.config;

import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.RegisteredUser;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );


//        throw new UsernameNotFoundException("User type not supported: " + user.getClass().getName());
    }
}
