package com.lukasz.project.service;

import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Role;
import com.lukasz.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminRepository adminRepository;

    public void createAdmin(Admin admin){
        // Dodajemy logikę sprawdzania, czy rekruter o podanym emailu już istnieje
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Admin with this email already exists");
        }

        // TODO: 12/1/2023 role
        admin.setRole(Role.ADMIN);

        adminRepository.save(admin);
    }


    public List<Admin> findAllAdmin(){
        return adminRepository.findAll();
    }



}
