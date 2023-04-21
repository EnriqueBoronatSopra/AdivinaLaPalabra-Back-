package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import com.soprasteria.adivinaLaPalabra.dto.WordExistResponse;
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
        WordExistResponse wordExistResponse = wordExistService.checkWord(WORD1);
        boolean expectedResult = wordExistResponse.isWordExists();
        assertTrue(expectedResult);
    }

    @Test
    void returnFalseIfWordNotExist() {
        final String WORD2 = "fsofb";
        when(wordsReaderMock.checkWord(WORD2)).thenReturn(false);
        WordExistResponse wordExistResponse = wordExistService.checkWord(WORD2);
        boolean expectedResult = wordExistResponse.isWordExists();
        assertFalse(expectedResult);
    }

}