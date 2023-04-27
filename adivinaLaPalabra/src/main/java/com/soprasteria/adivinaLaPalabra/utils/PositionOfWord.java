package com.soprasteria.adivinaLaPalabra.utils;

import lombok.Data;

@Data
public class PositionOfWord {
    public static final String HIT = "hit";
    public static final String PARTIAL_HIT = "partialHit";
    public static final String FAIL = "fail";

    private char letter;
    private String hitStatus;
    private int position;
    private boolean positionRevised;

    public PositionOfWord(char letter, int position) {
        this.letter = letter;
        this.hitStatus = FAIL;
        this.position = position;
        this.positionRevised = false;
    }

    public boolean positionEquals(PositionOfWord positionOfWord) {
        return (this.letter == positionOfWord.letter && this.position == positionOfWord.position);
    }

    public void positionRevised() {
        this.positionRevised = true;
    }
}
