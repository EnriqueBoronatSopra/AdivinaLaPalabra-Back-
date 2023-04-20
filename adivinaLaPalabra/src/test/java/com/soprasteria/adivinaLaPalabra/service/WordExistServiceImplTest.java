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
    private WordExistServiceImpl wordExistService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnTrueIfWordExist() {
        final String WORD1 = "abaca";
        when(wordsReaderMock.checkWord(WORD1)).thenReturn(true);
        boolean expectedResult = wordExistService.checkWord(WORD1);
        assertTrue(expectedResult);
    }

    @Test
    void returnFalseIfWordNotExist() {
        final String WORD2 = "fsofb";
        when(wordsReaderMock.checkWord(WORD2)).thenReturn(false);
        boolean expectedResult = wordExistService.checkWord(WORD2);
        assertFalse(expectedResult);
    }

}