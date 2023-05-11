package com.soprasteria.adivinalapalabra.model;

import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

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

    @ManyToOne
    @JoinColumn(name = "idUser")
    @ToString.Exclude
    private UserEntity user;

    public void incrementIntentInOne() {
        intentNumber++;
    }
    public void dateTimeNow() {
        dateTime = LocalDateTime.now();
    }





}
