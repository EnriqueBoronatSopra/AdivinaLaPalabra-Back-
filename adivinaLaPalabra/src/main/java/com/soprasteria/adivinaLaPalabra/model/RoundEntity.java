package com.soprasteria.adivinaLaPalabra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @GeneratedValue
    private final Long id;
    private String word;

}
