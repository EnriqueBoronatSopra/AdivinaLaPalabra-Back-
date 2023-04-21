package com.soprasteria.adivinaLaPalabra.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @InjectMocks
    private Round round;

    @Test
    void checkThatIdIsNotNull() {
        round = new Round("araba");
        assertNotNull(round.getId());
    }

}