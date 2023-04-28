package com.soprasteria.adivinalapalabra.dto;

import com.soprasteria.adivinalapalabra.model.enums.HitsStatus;
import lombok.Data;

@Data
public class PositionOfWordResponse {

    private char letter;
    private HitsStatus hitStatus;

    public PositionOfWordResponse(char letter, HitsStatus hitStatus) {
        this.letter = letter;
        this.hitStatus = hitStatus;
    }
}
