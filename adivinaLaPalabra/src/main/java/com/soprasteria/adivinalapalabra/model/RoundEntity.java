package com.soprasteria.adivinalapalabra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.catalina.User;

@Data
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private int intentNumber;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @ToString.Exclude
    private UserEntity user;
    public void incrementIntentInOne() {
        intentNumber++;
    }

}
