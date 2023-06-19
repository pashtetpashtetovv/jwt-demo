package com.example.jwtdemo.controller;

import com.example.jwtdemo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @GetMapping("for-user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forUser(){
        return "Hello, User;";
    }

    @GetMapping("for-admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String forAdmin(){
        return "Hello, Admin;";
    }

    @GetMapping("for-moder")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String forModer(){
        return "Hello, Moder;";
    }

}
