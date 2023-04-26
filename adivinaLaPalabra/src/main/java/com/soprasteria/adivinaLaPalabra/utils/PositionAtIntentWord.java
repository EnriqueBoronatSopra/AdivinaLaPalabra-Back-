package com.soprasteria.adivinaLaPalabra.utils;

import lombok.Data;

@Data
public class PositionAtIntentWord {
    public static final String HIT = "hit";
    public static final String PARTIAL_HIT = "partialHit";
    public static final String FAIL = "fail";

    private char charAtPosition;
    private String hitStatus;

    public PositionAtIntentWord(char charAtPosition) {
        this.charAtPosition = charAtPosition;
    }
}
