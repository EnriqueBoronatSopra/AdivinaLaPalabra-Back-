package com.soprasteria.adivinalapalabra.config;

import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordsRepositoryTest {

    private WordsRepository wordsRepository;

    @BeforeEach
    void setup() {
        wordsRepository = new WordsRepository();
    }

    @Test
    void testReturnTrueIfWordExist() {
        final String firstWord = "zuzon";
        boolean result = wordsRepository.checkWord(firstWord);

        assertTrue(result);
    }

    @Test
    void testReturnFalseIfWordNotExist() {
        final String firstWord = "aeiou";
        boolean result = wordsRepository.checkWord(firstWord);

        assertFalse(result);
    }

    @Test
    void testConvertJsonToList() {
        List<String> words = wordsRepository.getDictionary();
        assertNotNull(words);
    }

    @Test
    void testRandomWordReturnNotNull() {
        String randomWord = wordsRepository.getRandomWord();

        assertNotNull(randomWord);
    }

}