package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.soprasteria.adivinalapalabra.utils.ErrorMsgs.INCORRECT_LOGIN;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String response = loginService.login(loginRequest);
        return !response.equals(INCORRECT_LOGIN)?
                ResponseEntity.ok(response) :
                new ResponseEntity<>(INCORRECT_LOGIN, HttpStatus.UNAUTHORIZED);
    }
}
