package com.soprasteria.adivinaLaPalabra.dto;

import lombok.Data;

@Data
public class WordResponse {
    private boolean wordExists;

    public WordResponse(boolean wordExists) {
        this.wordExists = wordExists;
    }
}
