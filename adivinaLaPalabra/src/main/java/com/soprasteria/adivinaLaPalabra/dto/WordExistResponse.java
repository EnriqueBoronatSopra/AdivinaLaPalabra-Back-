package com.soprasteria.adivinaLaPalabra.dto;

import lombok.Data;

@Data
public class WordExistResponse {
    private boolean wordExists;

    public WordExistResponse(boolean wordExists) {
        this.wordExists = wordExists;
    }
}
