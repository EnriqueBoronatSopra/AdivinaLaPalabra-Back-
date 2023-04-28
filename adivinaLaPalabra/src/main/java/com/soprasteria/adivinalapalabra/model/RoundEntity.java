package com.soprasteria.adivinalapalabra.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;

}
