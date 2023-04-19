package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordExistServiceImpl  implements WordExistService {

    @Autowired
    WordsReader wordsReader;

    @Override
    public boolean existWordOrNot(String word) {
        return wordsReader.checkWord(word);
    }
}
