package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.IRoundEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class NewRoundServiceImpl implements INewRoundService {
    @Autowired
    WordsReader wordsReader;
    @Autowired
    IRoundEntityRepository roundEntityRepository;
    @Override
    public RoundResponse newRound() {
        String wordToGuess = wordsReader.getRandomWord();
        RoundResponse roundResponse = new RoundResponse();

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setWord(wordToGuess);
        RoundEntity savedRoundEntity = roundEntityRepository.save(roundEntity);

        Long idRoundSaved = savedRoundEntity.getId();
        roundResponse.setId(idRoundSaved);
        return roundResponse;
    }
}
