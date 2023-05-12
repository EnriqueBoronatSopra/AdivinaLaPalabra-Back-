package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.HistoricalResponse;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface RoundService {
    RoundResponse newRound(UserEntity userEntity);

    Optional<RoundResponse> getRound(long id);

    HistoricalResponse lastTenRounds(UserEntity userEntity);

    RoundResponse parseRound(RoundEntity roundEntity);

    int updateIntentNumber(long id);

    void setRoundWin(long id);
}
