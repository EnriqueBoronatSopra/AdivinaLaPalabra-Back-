package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.repository.AllowWords;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WordExistServiceImplTest {

    @Mock
    private AllowWords allowWordsMock;

    @InjectMocks
    private WordExistServiceImpl wordExistService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnTrueIfWordExist() {
        final String WORD1 = "abaca";
        when(allowWordsMock.checkWord(WORD1)).thenReturn(true);
        WordResponse wordResponse = wordExistService.checkWord(WORD1);
        boolean expectedResult = wordResponse.isWordExists();
        assertTrue(expectedResult);
    }

    @Test
    void returnFalseIfWordNotExist() {
        final String WORD2 = "fsofb";
        when(allowWordsMock.checkWord(WORD2)).thenReturn(false);
        WordResponse wordResponse = wordExistService.checkWord(WORD2);
        boolean expectedResult = wordResponse.isWordExists();
        assertFalse(expectedResult);
    }

}