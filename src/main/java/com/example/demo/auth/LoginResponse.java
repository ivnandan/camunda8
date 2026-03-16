package com.example.demo.auth;

public class LoginResponse {

    private final String token;
    private final String type = "Bearer";

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}