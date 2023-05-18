package com.iniciador.utils;

public class AuthOutput {
    private String accessToken;

    public AuthOutput(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
