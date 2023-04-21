package com.soprasteria.adivinaLaPalabra.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class RoundEntityTest {
    @InjectMocks
    private RoundEntity roundEntity;

    @Test
    void checkThatIdIsNotNull() {
        roundEntity = new RoundEntity("araba");
        assertNotNull(roundEntity.getId());
    }

}