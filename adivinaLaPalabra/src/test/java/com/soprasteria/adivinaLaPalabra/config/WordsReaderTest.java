package com.soprasteria.adivinaLaPalabra.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordsReaderTest {

    private WordsReader wordsReader;

    @BeforeEach
    void setup() {
        wordsReader = new WordsReader();
    }

    @Test
    void testReturnTrueIfWordExist() {
        final String firstWord = "zuzon";
        boolean result = wordsReader.checkWord(firstWord);

        assertTrue(result);
    }

    @Test
    void testReturnFalseIfWordNotExist() {
        final String firstWord = "aeiou";
        boolean result = wordsReader.checkWord(firstWord);

        assertFalse(result);
    }

    @Test
    void testConvertJsonToList() {
        List<String> words = wordsReader.getDictionary();
        assertNotNull(words);
    }

    @Test
    void testRandomWordReturnNotNull() {
        String randomWord = wordsReader.getRandomWord();

        assertNotNull(randomWord);
    }

}