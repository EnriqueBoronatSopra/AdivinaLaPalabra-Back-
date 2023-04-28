package com.soprasteria.adivinalapalabra.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Component
public class WordsRepository {

    private List<String> dictionary;

    public WordsRepository() {
        convertJsonToList();
    }

    private void convertJsonToList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/dictionary.json")) {
            dictionary = objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkWord(String word) {
        return dictionary.contains(word);
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public String getRandomWord() {
        Random random = new Random();
        int dictionarySize = dictionary.size();
        int randomIndex = random.nextInt(dictionarySize);

        return dictionary.get(randomIndex);
    }
}
