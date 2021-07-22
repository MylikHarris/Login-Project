package com.example.logintutorial;

public class LoginRequest {

    final String username;
    final String password;

    LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
