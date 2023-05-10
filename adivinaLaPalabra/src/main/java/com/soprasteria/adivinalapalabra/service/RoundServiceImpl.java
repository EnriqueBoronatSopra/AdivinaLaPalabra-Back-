package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.repository.WordsRepository;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.repository.RoundRepository;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.jwt.JWTProvider;
import com.soprasteria.adivinalapalabra.security.jwt.JWTTokenFilter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundServiceImpl implements RoundService {

    private static final Logger logger = LoggerFactory.getLogger(RoundService.class);

    @Autowired
    private WordsRepository wordsRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Override
    public RoundResponse newRound(UserEntity userEntity) {
        String wordToGuess = wordsRepository.getRandomWord();

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setWord(wordToGuess);
        roundEntity.setIntentNumber(0);
        roundEntity.setUser(userEntity);

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

    @Override
    public Optional<RoundResponse> getRound(long id) {
        try {
            return Optional.ofNullable(parseRound(roundRepository.getReferenceById(id)));
        } catch (EntityNotFoundException exception) {
            logger.error("Entity not found");
            return Optional.empty();
        }
    }

    @Override
    public RoundResponse parseRound(RoundEntity roundEntity) {
        RoundResponse roundResponse = new RoundResponse();

        roundResponse.setId(roundEntity.getId());
        roundResponse.setWord(roundEntity.getWord());

        return roundResponse;
    }

    @Override
    public int updateIntentNumber(long id) {
        RoundEntity roundEntity = roundRepository.getReferenceById(id);
        roundEntity.incrementIntentInOne();
        roundRepository.save(roundEntity);

        return roundEntity.getIntentNumber();
    }
}
