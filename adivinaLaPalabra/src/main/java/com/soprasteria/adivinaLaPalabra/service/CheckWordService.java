package com.soprasteria.adivinaLaPalabra.service;


import com.soprasteria.adivinaLaPalabra.dto.WordResponse;

public interface CheckWordService {
    WordResponse checkWord(String word, long id);
}
