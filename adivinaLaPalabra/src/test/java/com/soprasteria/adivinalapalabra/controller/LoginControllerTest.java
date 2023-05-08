package com.soprasteria.adivinalapalabra.controller;

import com.soprasteria.adivinalapalabra.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import static com.soprasteria.adivinalapalabra.utils.ErrorMsgs.INCORRECT_LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    private static final String SECRET_WORD = "T4k3_Up23";
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_WORD.getBytes());
    @Mock
    private LoginServiceImpl loginService;

    @InjectMocks
    private LoginController LoginController;

    @Test
    void correctLoginTest() {
        String name = "name";
        String password = "password";
        final int tokenValidity = 900000;
        LoginRequest loginRequest = new LoginRequest(name, password);
        String token = Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .compact();

        when(loginService.login(loginRequest)).thenReturn(token);

        String request = loginService.login(loginRequest);

        assertEquals(token, request);
    }

    @Test
    void incorrectLoginTest() {
        String requestExpected = "User or password incorrect";
        String name = "name";
        String password = "password";
        LoginRequest loginRequest = new LoginRequest(name, password);

        when(loginService.login(loginRequest)).thenReturn(INCORRECT_LOGIN);

        String request = loginService.login(loginRequest);

        assertEquals(requestExpected, request);
    }
}
