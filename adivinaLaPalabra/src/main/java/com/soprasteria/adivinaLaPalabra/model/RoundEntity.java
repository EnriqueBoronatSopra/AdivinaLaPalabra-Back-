package com.soprasteria.adivinaLaPalabra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    private UUID id;
    private String word;

    public RoundEntity(String word) {
        this.word = word;
        this.id = UUID.randomUUID();
    }
}
