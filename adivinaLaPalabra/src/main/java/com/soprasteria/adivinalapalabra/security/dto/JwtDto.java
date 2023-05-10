package com.soprasteria.adivinalapalabra.security.dto;

public class JwtDto {
    private String token;

    private final String bearer = "Bearer";

    private String userName;

    public JwtDto(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
