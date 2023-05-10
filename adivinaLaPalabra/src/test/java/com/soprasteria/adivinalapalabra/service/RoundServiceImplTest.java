package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.service.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class RoundServiceImplTest {
    @Mock
    private RoundRepository roundRepository;

    @Mock
    private WordsRepository wordsRepository;

    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private RoundServiceImpl roundService;

    @BeforeEach
    void setup() {
        openMocks(this);

        final Long ID = 3L;
        final String WORD_TO_GUESS = "abaca";

        RoundEntity roundEntitySaved = new RoundEntity();
        roundEntitySaved.setId(ID);
        when(roundRepository.save(any())).thenReturn(roundEntitySaved);
        when(wordsRepository.getRandomWord()).thenReturn(WORD_TO_GUESS);
        when(loginService.getUserFromToken(any())).thenReturn(new UserEntity());
    }

    @Test
    void testIfNewRoundServiceReturnARoundResponseInstance() {
        assertInstanceOf(RoundResponse.class, roundService.newRound(new UserEntity()));
    }

    @Test
    void testIfNewRoundServiceNotReturnNull() {
        assertNotNull(roundService.newRound(new UserEntity()));
    }
}