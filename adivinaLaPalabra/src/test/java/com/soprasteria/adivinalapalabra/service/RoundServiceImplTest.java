package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoundServiceImplTest {
    @Mock
    private RoundRepository roundRepository;

    @Mock
    private WordsRepository wordsRepository;

    @InjectMocks
    private RoundServiceImpl newRoundService;

    @BeforeEach
    void setup() {
        openMocks(this);

        final Long ID = 3L;
        final String WORD_TO_GUESS = "abaca";

        RoundEntity roundEntitySaved = new RoundEntity();
        roundEntitySaved.setId(ID);
        when(roundRepository.save(any())).thenReturn(roundEntitySaved);
        when(wordsRepository.getRandomWord()).thenReturn(WORD_TO_GUESS);
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