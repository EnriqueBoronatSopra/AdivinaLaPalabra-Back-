package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.configuration.GameConfiguration;
import com.soprasteria.adivinalapalabra.dto.RoundResponse;
import com.soprasteria.adivinalapalabra.dto.WordResponse;
import com.soprasteria.adivinalapalabra.model.RoundEntity;
import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.service.LoginServiceImpl;
import com.soprasteria.adivinalapalabra.service.RoundServiceImpl;
import com.soprasteria.adivinalapalabra.service.WordServiceImpl;
import com.soprasteria.adivinalapalabra.utils.ErrorMsgs;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundServiceImpl roundService;

    @Autowired
    private WordServiceImpl wordService;

    @Autowired
    private LoginServiceImpl loginService;



    @PostMapping
    public ResponseEntity<RoundResponse> newRound(HttpServletRequest httpServletRequest) {

        UserEntity user = loginService.getUserFromToken(httpServletRequest);
        RoundResponse roundResponse = roundService.newRound(user);
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

        if (wordResponse.isRoundWin()) {
            roundService.setRoundWin(idRound);
        }

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

    @GetMapping
    public ResponseEntity<List<RoundEntity>> lastTen(HttpServletRequest httpServletRequest) {
        UserEntity user = loginService.getUserFromToken(httpServletRequest);
        List<RoundEntity> listRounds = roundService.all(user).stream()
                                                                .limit(10)
                                                                .toList();
        return !listRounds.isEmpty() ? ResponseEntity.ok(listRounds) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}