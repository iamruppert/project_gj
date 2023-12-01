package com.lukasz.project.controller;

import com.lukasz.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")

public class UserController {

    @Autowired
    private UserService userService;


}
