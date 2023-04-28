package com.soprasteria.adivinalapalabra.model;

import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoundEntityTest {

    @Mock
    private RoundRepository roundRepository;

    private RoundEntity roundEntity;

    @BeforeEach
    void setup() {
        openMocks(this);
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