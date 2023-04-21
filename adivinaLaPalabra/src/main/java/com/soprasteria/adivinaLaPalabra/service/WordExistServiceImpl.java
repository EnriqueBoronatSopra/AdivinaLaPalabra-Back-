package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import com.soprasteria.adivinaLaPalabra.dto.WordExistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class WordExistServiceImpl  implements WordExistService {

    @Autowired
    private WordsReader wordsReader;

    @Override
    public WordExistResponse checkWord(String word) {
        String lowerCaseWord = word.toLowerCase();
        boolean wordExist = wordsReader.checkWord(lowerCaseWord);

        return new WordExistResponse(wordExist);
    }
}
