package com.soprasteria.adivinaLaPalabra.service;

import com.soprasteria.adivinaLaPalabra.config.WordsReader;
import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.model.RoundEntity;
import com.soprasteria.adivinaLaPalabra.repository.IRoundEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

public class NewRoundServiceImpl implements INewRoundService {

    private static final Logger logger = LoggerFactory.getLogger(INewRoundService.class);
    @Autowired
    WordsReader wordsReader;
    @Autowired
    IRoundEntityRepository roundEntityRepository;
    @Override
    public RoundResponse newRound() {
        String wordToGuess = wordsReader.getRandomWord();
        RoundResponse roundResponse = null;

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setWord(wordToGuess);

        try {
            RoundEntity savedRoundEntity = roundEntityRepository.save(roundEntity);
            roundResponse = new RoundResponse();
            Long idRoundSaved = savedRoundEntity.getId();
            roundResponse.setId(idRoundSaved);
        } catch (DataAccessResourceFailureException exception) {
            logger.error("Error saving the round in the Database");
        }
        return roundResponse;
    }
}
