package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        final String CORRECT_LOGIN = "Correct login";
        final String INCORRECT_LOGIN = "Incorrect login";
        return loginService.login(loginRequest).equals("Correct login")?
                ResponseEntity.ok(CORRECT_LOGIN) :
                new ResponseEntity<>(INCORRECT_LOGIN, HttpStatus.UNAUTHORIZED);
    }
}
