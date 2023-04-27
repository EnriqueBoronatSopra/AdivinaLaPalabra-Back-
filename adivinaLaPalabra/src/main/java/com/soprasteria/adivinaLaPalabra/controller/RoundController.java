package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.dto.WordResponse;
import com.soprasteria.adivinaLaPalabra.service.RoundServiceImpl;
import com.soprasteria.adivinaLaPalabra.service.CheckWordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundServiceImpl roundService;

    @Autowired
    private CheckWordServiceImpl wordService;

    @PostMapping
    public ResponseEntity<RoundResponse> newRound() {
        RoundResponse roundResponse = roundService.newRound();
        return roundResponse != null ? ResponseEntity.ok(roundResponse) :
                new ResponseEntity<>(roundResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/{idRound}/check-word")
    public ResponseEntity<WordResponse> wordExist(@RequestParam String word, @PathVariable Long idRound) {
        WordResponse wordResponse = wordService.checkWord(word, idRound);

        return wordResponse.isWordExists()?
                ResponseEntity.ok(wordResponse): new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);
    }
}
