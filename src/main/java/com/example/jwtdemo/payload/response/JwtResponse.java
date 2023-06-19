package com.example.jwtdemo.payload.response;

public class JwtResponse {

    private String token;

    private final static String tokenType = "Bearer";

    private Long userId;

    private String username;

    private String email;

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
