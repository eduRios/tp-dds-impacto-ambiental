package com.dds.tpimpactoambiental.dtos.response;

import org.springframework.http.HttpStatus;


public class LoginResponse extends Response {
    private String accessToken;

    public LoginResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
