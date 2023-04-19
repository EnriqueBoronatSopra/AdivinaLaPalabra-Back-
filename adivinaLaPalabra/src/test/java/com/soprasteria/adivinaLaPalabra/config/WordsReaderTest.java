package com.soprasteria.adivinaLaPalabra.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordsReaderTest {

    private WordsReader wordsReader;

    @BeforeEach
    public void setup() {
        wordsReader = WordsReader.getInstance();
    }

    @Test
    void testWordExistReturnTrueWhenTheWordIsInDictionary() {
        final String firstWord = "zuzon";
        boolean result = wordsReader.checkWord(firstWord);

        assertTrue(result);
    }

    @Test
    void testWordExistReturnFalseWhenTheWordNotIsInDictionary() {
        final String firstWord = "aeiou";
        boolean result = wordsReader.checkWord(firstWord);

        assertFalse(result);
    }

    @Test
    void testGetInstanceReturnTheSameInstanceOfTheObject() {
        WordsReader instance1 = WordsReader.getInstance();
        WordsReader instance2 = WordsReader.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void testConvertJsonToList() {
        List<String> words = wordsReader.getWords();
        assertNotNull(words);
    }
}