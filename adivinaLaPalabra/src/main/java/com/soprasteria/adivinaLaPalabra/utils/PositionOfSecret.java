package com.soprasteria.adivinaLaPalabra.utils;

import lombok.Data;

@Data
public class PositionOfSecret {

    private char charAtPosition;
    private boolean positionRevised;

    public PositionOfSecret(char charAtPosition) {
        this.charAtPosition = charAtPosition;
        this.positionRevised = false;
    }
}
