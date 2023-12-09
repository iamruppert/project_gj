package com.lukasz.project.service;

import com.lukasz.project.model.RegisteredUser;

public interface RegisteredUserService {

    void updateUser(RegisteredUser user);

    RegisteredUser findUserByEmail(String email);
}
