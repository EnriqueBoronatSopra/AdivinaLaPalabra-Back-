package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {

    private static final Logger logger = LoggerFactory.getLogger(RoundService.class);

    @Autowired
    private WordsRepository wordsRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Override
    public RoundResponse newRound() {
        String wordToGuess = wordsRepository.getRandomWord();

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setWord(wordToGuess);

        try {
            RoundEntity savedRoundEntity = roundRepository.save(roundEntity);
            Long idRoundSaved = savedRoundEntity.getId();
            RoundResponse roundResponse = new RoundResponse();
            roundResponse.setId(idRoundSaved);
            return roundResponse;
        } catch (DataAccessResourceFailureException exception) {
            logger.error("Error saving the round in the Database");
        }
        return null;
    }
}
