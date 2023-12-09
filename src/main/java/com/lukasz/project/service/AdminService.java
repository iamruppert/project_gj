package com.lukasz.project.service;

import com.lukasz.project.model.Admin;

import java.util.List;

public interface AdminService {

    void createAdmin(Admin admin);
    List<Admin> findAllAdmin();
}
