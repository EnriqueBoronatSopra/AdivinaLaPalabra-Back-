package com.soprasteria.adivinalapalabra.dto;

import com.soprasteria.adivinalapalabra.model.enums.HitsStatus;
import lombok.Data;

@Data
public class PositionOfWord {

    private char letter;
    private HitsStatus hitStatus;
    private int position;
    private boolean positionRevised;

    public PositionOfWord(char letter, int position) {
        this.letter = letter;
        this.hitStatus = HitsStatus.FAIL;
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
