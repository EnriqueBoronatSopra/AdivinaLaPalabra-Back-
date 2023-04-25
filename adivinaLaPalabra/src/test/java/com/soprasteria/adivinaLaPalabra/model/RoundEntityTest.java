package com.soprasteria.adivinaLaPalabra.model;

import com.soprasteria.adivinaLaPalabra.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoundEntityTest {

    @Mock
    private RoundRepository roundRepository;

    private RoundEntity roundEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        RoundEntity roundEntitySaved = new RoundEntity();
        roundEntitySaved.setId(3L);
        when(roundRepository.save(roundEntity)).thenReturn(roundEntitySaved);
    }

    @Test
    void checkThatIdIsNotNull() {
        RoundEntity roundEntityExpected = roundRepository.save(roundEntity);
        assertNotNull(roundEntityExpected.getId());
    }

}