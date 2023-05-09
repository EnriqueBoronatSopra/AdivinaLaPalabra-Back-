package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<UserDetails> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails response = loginService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
