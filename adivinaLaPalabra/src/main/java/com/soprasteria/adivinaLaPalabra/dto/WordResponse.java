package com.soprasteria.adivinaLaPalabra.dto;

import lombok.Data;

import java.util.List;

@Data
public class WordResponse {

    private boolean wordExists;
    private List<PositionOfWordResponse> positionOfWordResponseList;

    public WordResponse(boolean wordExists, List<PositionOfWordResponse> positionOfWordResponseList) {
        this.wordExists = wordExists;
        this.positionOfWordResponseList = positionOfWordResponseList;
    }
}
