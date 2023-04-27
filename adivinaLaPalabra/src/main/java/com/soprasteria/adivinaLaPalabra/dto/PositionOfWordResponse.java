package com.soprasteria.adivinaLaPalabra.dto;

import lombok.Data;

@Data
public class PositionOfWordResponse {

    private char letter;
    private String hitStatus;

    public PositionOfWordResponse(char letter, String hitStatus) {
        this.letter = letter;
        this.hitStatus = hitStatus;
    }
}
