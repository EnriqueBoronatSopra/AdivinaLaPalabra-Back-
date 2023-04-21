package com.soprasteria.adivinaLaPalabra.service;


import com.soprasteria.adivinaLaPalabra.dto.WordExistResponse;

public interface WordExistService {
    WordExistResponse checkWord(String word);
}
