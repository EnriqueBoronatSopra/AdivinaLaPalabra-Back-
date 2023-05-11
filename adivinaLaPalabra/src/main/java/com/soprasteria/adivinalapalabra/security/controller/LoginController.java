package com.soprasteria.adivinalapalabra.security.controller;

import com.soprasteria.adivinalapalabra.security.dto.JwtDto;
import com.soprasteria.adivinalapalabra.security.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.security.jwt.JWTProvider;
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

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest loginRequest) {
        String nameEncoded = loginRequest.getName();
        String passwordEncoded = loginRequest.getPassword();
        byte[] nameDecodedBytes = Base64.getDecoder().decode(nameEncoded);
        byte[] passwordDecodedBytes = Base64.getDecoder().decode(passwordEncoded);
        String name = new String(nameDecodedBytes);
        String password = new String(passwordDecodedBytes);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);

        return ResponseEntity.ok(jwtDto);
    }
}
