package com.soprasteria.adivinaLaPalabra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class WordsReader {
    private List<String> words;
    private static WordsReader wordsReaderInstance;

    private WordsReader() {
        convertJsonToList();
    }

    public static WordsReader getInstance() {
        if (wordsReaderInstance == null) {
            wordsReaderInstance = new WordsReader();
        }
        return wordsReaderInstance;
    }

    private void convertJsonToList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/dictionary.json")) {
            words = objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkWord(String word) {
        return words.contains(word);
    }

    public List<String> getWords() {
        return words;
    }
}
