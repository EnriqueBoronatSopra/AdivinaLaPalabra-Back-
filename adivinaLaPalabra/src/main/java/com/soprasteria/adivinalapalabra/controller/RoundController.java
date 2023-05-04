package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.configuration.GameConfiguration;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.service.RoundServiceImpl;
import com.soprasteria.adivinalapalabra.service.WordServiceImpl;
import com.soprasteria.adivinalapalabra.utils.ErrorMsgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundServiceImpl roundService;

    @Autowired
    private WordServiceImpl wordService;

    @PostMapping
    public ResponseEntity<RoundResponse> newRound() {
        RoundResponse roundResponse = roundService.newRound();
        return roundResponse != null ? ResponseEntity.ok(roundResponse) :
                new ResponseEntity<>(roundResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/{idRound}/check-word")
    public ResponseEntity<WordResponse> wordExist(@RequestParam String word, @PathVariable Long idRound) {
        Optional<RoundResponse> roundResponse = roundService.getRound(idRound);

        if (roundResponse.isEmpty()) {
            WordResponse wordResponse = new WordResponse(false, null);
            wordResponse.setErrorMsg(ErrorMsgs.ROUND_NOT_FOUND);
            return new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);
        }

        String secretWord = roundResponse.get().getWord();

        WordResponse wordResponse = wordService.checkWord(word, secretWord);
        if (wordResponse.isWordExists()) {
            int roundIntentNumber = roundService.updateIntentNumber(idRound);

            if (roundIntentNumber >= GameConfiguration.MAX_ATTEMPTS_ALLOWED) {
                wordResponse.setSecretWord(secretWord);
            }
            wordResponse.setRoundIntentNumber(roundIntentNumber);
            return ResponseEntity.ok(wordResponse);
        }
        
        return new ResponseEntity<>(wordResponse, HttpStatus.NOT_FOUND);
    }
}
