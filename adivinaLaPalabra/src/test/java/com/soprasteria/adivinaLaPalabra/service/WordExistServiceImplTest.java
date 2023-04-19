package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WordExistServiceImplTest {

    @Mock
    private WordsReader wordsReaderMock;

    @InjectMocks
    WordExistServiceImpl wordExistService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkIfWordExistServiceReturnTrueWhenWordExist() {
        final String WORD1 = "abaca";
        when(wordsReaderMock.checkWord(WORD1)).thenReturn(true);
        boolean expectedResult = wordExistService.existWordOrNot(WORD1);
        assertTrue(expectedResult);
    }

    @Test
    void checkIfWordExistServiceReturnTrueWhenWordNotExist() {
        final String WORD2 = "fsofb";
        when(wordsReaderMock.checkWord(WORD2)).thenReturn(false);
        boolean expectedResult = wordExistService.existWordOrNot(WORD2);
        assertFalse(expectedResult);
    }

}