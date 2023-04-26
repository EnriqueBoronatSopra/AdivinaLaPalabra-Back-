package com.soprasteria.adivinaLaPalabra.config;

import com.soprasteria.adivinaLaPalabra.repository.AllowWords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllowWordsTest {

    private AllowWords allowWords;

    @BeforeEach
    void setup() {
        allowWords = new AllowWords();
    }

    @Test
    void testReturnTrueIfWordExist() {
        final String firstWord = "zuzon";
        boolean result = allowWords.checkWord(firstWord);

        assertTrue(result);
    }

    @Test
    void testReturnFalseIfWordNotExist() {
        final String firstWord = "aeiou";
        boolean result = allowWords.checkWord(firstWord);

        assertFalse(result);
    }

    @Test
    void testConvertJsonToList() {
        List<String> words = allowWords.getDictionary();
        assertNotNull(words);
    }

    @Test
    void testRandomWordReturnNotNull() {
        String randomWord = allowWords.getRandomWord();

        assertNotNull(randomWord);
    }

}