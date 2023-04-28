package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import com.soprasteria.adivinalapalabra.service.RoundServiceImpl;
import com.soprasteria.adivinalapalabra.service.WordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class RoundControllerTest {

    @Mock
    private RoundServiceImpl roundService;

    @Mock
    private WordServiceImpl wordService;

    @Mock
    private RoundRepository roundRepository;

    @InjectMocks
    private RoundController roundController;

    @Test
    void returnIdTheNewRound() {
        RoundResponse roundResponseExpected = new RoundResponse();
        final Long id = 3L;
        roundResponseExpected.setId(id);
        when(roundService.newRound()).thenReturn(roundResponseExpected);

        ResponseEntity<RoundResponse> responseEntityExpected = ResponseEntity.ok(roundResponseExpected);

        assertEquals(responseEntityExpected, roundController.newRound());
    }

    @Test
    void returnIdNull(){
        when(roundService.newRound()).thenReturn(null);

        ResponseEntity<RoundResponse> responseEntityExpected =
                new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);

        assertEquals(responseEntityExpected, roundController.newRound());
    }

    @Test
    void returnFalseIsNotWord() {
        final String word = "word";
        final long id = 3L;
        final RoundEntity roundEntity = new RoundEntity();
        final RoundResponse roundResponse = new RoundResponse();
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        final Optional<RoundResponse> optionalRoundResponse = Optional.of(roundResponse);
        final WordResponse wordResponse = new WordResponse(false, positionOfWordResponseList);

        when(roundService.getRound(id)).thenReturn(optionalRoundResponse);
        when(roundRepository.getReferenceById(id)).thenReturn(roundEntity);
        when(roundRepository.getReferenceById(id).getWord()).thenReturn("word");
        when(wordService.checkWord(any(), any())).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);

        assertEquals(responseEntityExpected, roundController.wordExist(word, id));
    }

    @Test
    void returnTrueIsWord() {
        final String word = "word";
        final long id = 3L;
        final RoundEntity roundEntity = new RoundEntity();
        final RoundResponse roundResponse = new RoundResponse();
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        final Optional<RoundResponse> optionalRoundResponse = Optional.of(roundResponse);
        final WordResponse wordResponse = new WordResponse(true, positionOfWordResponseList);

        when(roundService.getRound(id)).thenReturn(optionalRoundResponse);
        when(roundRepository.getReferenceById(id)).thenReturn(roundEntity);
        when(roundRepository.getReferenceById(id).getWord()).thenReturn("word");
        when(wordService.checkWord(any(), any())).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = ResponseEntity.ok(wordResponse);

        assertEquals(responseEntityExpected, roundController.wordExist(word, id));
    }

}