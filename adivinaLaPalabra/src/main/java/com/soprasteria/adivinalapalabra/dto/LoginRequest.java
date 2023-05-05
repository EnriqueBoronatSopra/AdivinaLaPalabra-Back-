package com.soprasteria.adivinalapalabra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    @JsonProperty("name")
    private final String name;
    @JsonProperty("password")
    private final String password;
}
