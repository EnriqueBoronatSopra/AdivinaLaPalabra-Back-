package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.PositionOfWordResponse;
import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.RoundRepository;
import com.soprasteria.adivinaLaPalabra.service.RoundServiceImpl;
import com.soprasteria.adivinaLaPalabra.service.CheckWordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class RoundControllerTest {

    @Mock
    private RoundServiceImpl roundService;

    @Mock
    private CheckWordServiceImpl wordExistService;

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
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        WordResponse wordResponse = new WordResponse(false, positionOfWordResponseList);
        when(roundRepository.getReferenceById(id)).thenReturn(roundEntity);
        when(roundRepository.getReferenceById(id).getWord()).thenReturn("word");
        when(wordExistService.checkWord(any(), eq(3L))).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);

        assertEquals(responseEntityExpected, roundController.wordExist(word, id));
    }

    @Test
    void returnTrueIsWord() {
        final String word = "word";
        final long id = 3L;
        final RoundEntity roundEntity = new RoundEntity();
        final List<PositionOfWordResponse> positionOfWordResponseList = new ArrayList<>();
        WordResponse wordResponse = new WordResponse(true, positionOfWordResponseList);
        when(roundRepository.getReferenceById(id)).thenReturn(roundEntity);
        when(roundRepository.getReferenceById(id).getWord()).thenReturn("word");
        when(wordExistService.checkWord(any(), eq(3L))).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = ResponseEntity.ok(wordResponse);

        assertEquals(responseEntityExpected, roundController.wordExist(word, id));
    }

}