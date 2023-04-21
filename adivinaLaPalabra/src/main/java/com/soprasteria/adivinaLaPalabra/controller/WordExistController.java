package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.WordExistResponse;
import com.soprasteria.adivinaLaPalabra.service.WordExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordExistController {
    @Autowired
    private WordExistServiceImpl wordService;

    @GetMapping("/exist")
    public ResponseEntity<WordExistResponse> wordExist(@RequestParam String word) {
        WordExistResponse wordExistResponse= wordService.checkWord(word);

        return wordExistResponse.isWordExists()?
            ResponseEntity.ok(wordExistResponse): new ResponseEntity<>(wordExistResponse, HttpStatus.NOT_FOUND);
    }

}
