package com.soprasteria.adivinalapalabra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RoundResponse {

    private Long id;
    @JsonIgnore
    private String word;
}
