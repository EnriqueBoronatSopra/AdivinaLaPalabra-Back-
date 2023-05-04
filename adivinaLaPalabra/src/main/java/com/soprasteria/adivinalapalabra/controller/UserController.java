package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.UserDto;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        final String CORRECT_LOGIN = "Correct login";
        final String INCORRECT_LOGIN = "Incorrect login";
        return loginService.login(userDto).equals("Correct login")?
                ResponseEntity.ok(CORRECT_LOGIN) :
                new ResponseEntity<>(INCORRECT_LOGIN, HttpStatus.UNAUTHORIZED);
    }
}
