package com.lukasz.project.service;

import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Role;
import com.lukasz.project.model.User;
import com.lukasz.project.repository.AdminRepository;
import com.lukasz.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public void createAdmin(Admin admin) {
        // Dodajemy logikę sprawdzania, czy rekruter o podanym emailu już istnieje
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Admin with this email already exists");
        }

        admin.setRole(Role.ADMIN);

        adminRepository.save(admin);
    }

    @Override
    public List<Admin> findAllAdmin() {
        try {
            return adminRepository.findAllAdmins();
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error occurred while fetching recruiters", e);
        }
    }


    @Override
    public Page<User> findAllUsers(int page, int size) {
        try {
            return userRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new RuntimeException("Error occurred while fetching users", e);
        }
    }
}
