package com.soprasteria.adivinaLaPalabra.utils;

import lombok.Data;

@Data
public class PositionAtSecretWord {

    private char charAtPosition;
    private boolean positionRevised;

    public PositionAtSecretWord(char charAtPosition) {
        this.charAtPosition = charAtPosition;
        this.positionRevised = false;
    }
}
