package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;

import java.util.Optional;

public interface RoundService {
    RoundResponse newRound();

    Optional<RoundResponse> getRound(long id);

    RoundResponse parseRound(RoundEntity roundEntity);

    int updateIntentNumber(long id);
}
