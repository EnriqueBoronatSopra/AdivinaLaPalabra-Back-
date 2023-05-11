package com.soprasteria.adivinalapalabra.security.controller;

import com.soprasteria.adivinalapalabra.security.dto.JwtDto;
import com.soprasteria.adivinalapalabra.security.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.security.jwt.JWTProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest loginRequest) {
        String name = decodedString(loginRequest.getName());
        String password = decodedString(loginRequest.getPassword());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);

        return ResponseEntity.ok(jwtDto);
    }

    private String decodedString(String encodedString) {
        try {
            byte[] stringDecodedBytes = Base64.getDecoder().decode(encodedString);
            return new String(stringDecodedBytes);
        } catch (IllegalArgumentException exception) {
            logger.error("Error with bytes received");
        }
        return null;
    }
}
