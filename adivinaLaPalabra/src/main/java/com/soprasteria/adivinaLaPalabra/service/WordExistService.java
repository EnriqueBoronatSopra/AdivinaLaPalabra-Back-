package com.soprasteria.adivinaLaPalabra.service;


import com.soprasteria.adivinaLaPalabra.dto.WordResponse;

public interface WordExistService {
    WordResponse checkWord(String word);
}
