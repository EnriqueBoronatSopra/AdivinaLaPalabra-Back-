package com.soprasteria.adivinalapalabra.model;

import com.soprasteria.adivinalapalabra.utils.ErrorMsgs;
import jakarta.persistence.*;
import lombok.Data;

import javax.naming.Context;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<RoundEntity> listRound;
    




}
