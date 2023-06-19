package com.example.jwtdemo.payload.request;

import jakarta.validation.constraints.NotBlank;

public class SigninRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

    public SigninRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
