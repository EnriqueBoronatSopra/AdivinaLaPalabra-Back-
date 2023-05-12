package com.soprasteria.adivinalapalabra.dto;

import com.soprasteria.adivinalapalabra.model.RoundEntity;

import java.util.List;

public record HistoricalResponse(List<RoundEntity> lastTenRounds) { }
