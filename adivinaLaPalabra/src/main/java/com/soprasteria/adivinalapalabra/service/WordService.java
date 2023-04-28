package com.soprasteria.adivinalapalabra.service;


import com.soprasteria.adivinalapalabra.dto.WordResponse;

public interface WordService {
    WordResponse checkWord(String word, long id);
}
