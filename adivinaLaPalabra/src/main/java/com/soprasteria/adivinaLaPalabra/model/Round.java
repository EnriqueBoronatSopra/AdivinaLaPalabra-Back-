package com.soprasteria.adivinaLaPalabra.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Round {

    private UUID id;
    private String word;

    public Round(String word) {
        this.word = word;
        this.id = UUID.randomUUID();
    }
}
