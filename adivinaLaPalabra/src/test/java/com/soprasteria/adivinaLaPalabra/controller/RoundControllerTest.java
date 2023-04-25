package com.soprasteria.adivinaLaPalabra.controller;

import com.soprasteria.adivinaLaPalabra.dto.RoundResponse;
import com.soprasteria.adivinaLaPalabra.service.RoundService;
import com.soprasteria.adivinaLaPalabra.service.RoundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class RoundControllerTest {

    @Mock
    private RoundServiceImpl roundService;

    @InjectMocks
    private RoundController roundController;

    @Test
    void returnIdTheNewRound() {
        RoundResponse roundResponseExpected = new RoundResponse();
        final Long id = 3L;
        roundResponseExpected.setId(id);
        when(roundService.newRound()).thenReturn(roundResponseExpected);

        ResponseEntity<RoundResponse> responseEntityExpected = ResponseEntity.ok(roundResponseExpected);

        assertEquals(responseEntityExpected, roundController.newRound());
    }

    @Test
    void returnIdNull(){
        when(roundService.newRound()).thenReturn(null);

        ResponseEntity<RoundResponse> responseEntityExpected =
                new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);

        assertEquals(responseEntityExpected, roundController.newRound());
    }

}