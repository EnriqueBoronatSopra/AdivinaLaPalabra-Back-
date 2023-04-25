package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import com.soprasteria.adivinaLaPalabra.service.WordExistServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WordExistControllerTest {

    @Mock
    private WordExistServiceImpl wordExistService;

    @InjectMocks
    private WordExistController wordExistController;

    @Test
    void returnFalseIsNotWord() {
        final String word = "word";
        WordResponse wordResponse = new WordResponse(false);
        when(wordExistService.checkWord(any())).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);

        assertEquals(responseEntityExpected, wordExistController.wordExist(word));
    }

    @Test
    void returnTrueIsWord() {
        final String word = "word";
        WordResponse wordResponse = new WordResponse(true);
        when(wordExistService.checkWord(any())).thenReturn(wordResponse);

        ResponseEntity<WordResponse> responseEntityExpected = ResponseEntity.ok(wordResponse);

        assertEquals(responseEntityExpected, wordExistController.wordExist(word));
    }


}