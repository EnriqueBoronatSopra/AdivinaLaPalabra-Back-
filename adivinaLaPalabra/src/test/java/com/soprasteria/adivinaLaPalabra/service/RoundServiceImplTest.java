package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.repository.AllowWords;
import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RoundServiceImplTest {
    @Mock
    private RoundRepository roundRepository;

    @Mock
    private AllowWords allowWords;

    @InjectMocks
    private RoundServiceImpl newRoundService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        final Long ID = 3L;
        final String WORD_TO_GUESS = "abaca";

        RoundEntity roundEntitySaved = new RoundEntity();
        roundEntitySaved.setId(ID);
        when(roundRepository.save(any())).thenReturn(roundEntitySaved);
        when(allowWords.getRandomWord()).thenReturn(WORD_TO_GUESS);
    }

    @Test
    void testIfNewRoundServiceReturnARoundResponseInstance() {
        assertInstanceOf(RoundResponse.class, newRoundService.newRound());
    }

    @Test
    void testIfNewRoundServiceNotReturnNull() {
        assertNotNull(newRoundService.newRound());
    }
}