package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.service.WordExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Boolean> wordExist(@RequestParam String word) {
        boolean wordExistInDictionary = wordService.checkWord(word);
        return ResponseEntity.ok(wordExistInDictionary);
    }

}
