package com.soprasteria.adivinalapalabra.security.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtDto {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
