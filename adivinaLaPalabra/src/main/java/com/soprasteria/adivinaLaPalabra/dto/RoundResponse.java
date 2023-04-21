package com.soprasteria.adivinaLaPalabra.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RoundResponse {
    private UUID id;
    private String message;
}
