package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.service.RoundService;
import com.soprasteria.adivinaLaPalabra.service.RoundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundServiceImpl roundService;

    @PostMapping("/new-round")
    public ResponseEntity<RoundResponse> newRound() {
        RoundResponse roundResponse = roundService.newRound();
        return roundResponse != null ? ResponseEntity.ok(roundResponse) :
                new ResponseEntity<>(roundResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
