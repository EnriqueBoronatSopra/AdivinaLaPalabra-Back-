package com.soprasteria.adivinalapalabra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "round")
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private int intentNumber;
    private LocalDateTime dateTime;
    private Boolean roundWin;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @ToString.Exclude
    @JsonIgnore
    private UserEntity user;

    public void incrementIntentInOne() {
        intentNumber++;
    }
    public void dateTimeNow() {
        dateTime = LocalDateTime.now();
    }

    public void isRoundWin() {
        roundWin = true;
    }
}
