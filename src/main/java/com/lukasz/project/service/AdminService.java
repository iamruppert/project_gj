package com.lukasz.project.service;

import com.lukasz.project.model.Admin;
import com.lukasz.project.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    void createAdmin(Admin admin);
    List<Admin> findAllAdmin();
    Page<User> findAllUsers(int page, int size);
}
