package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.IRoundEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NewRoundServiceImplTest {
    @Mock
    private IRoundEntityRepository roundEntityRepository;

    @Mock
    WordsReader wordsReader;

    @InjectMocks
    NewRoundServiceImpl newRoundService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        final Long ID = 3L;
        final String WORD_TO_GUESS = "abaca";

        RoundEntity roundEntitySaved = new RoundEntity();
        roundEntitySaved.setId(ID);
        when(roundEntityRepository.save(any())).thenReturn(roundEntitySaved);
        when(wordsReader.getRandomWord()).thenReturn(WORD_TO_GUESS);
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