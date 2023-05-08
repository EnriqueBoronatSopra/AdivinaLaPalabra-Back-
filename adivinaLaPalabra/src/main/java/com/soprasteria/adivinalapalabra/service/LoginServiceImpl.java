package com.soprasteria.adivinalapalabra.service;

import com.soprasteria.adivinalapalabra.dto.LoginRequest;
import com.soprasteria.adivinalapalabra.model.UserEntity;
import com.soprasteria.adivinalapalabra.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import static com.soprasteria.adivinalapalabra.utils.ErrorMsgs.INCORRECT_LOGIN;

@Service
public class LoginServiceImpl implements LoginService {

    private static final String SECRET_WORD = "T4k3_Up23";

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository.findByName(loginRequest.getName());

        if (user.isEmpty() || !user.get().getPassword().equals(loginRequest.getPassword())) {
            return INCORRECT_LOGIN;
        }

        final String SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_WORD.getBytes());
        final int tokenValidity = 900000;

        return Jwts.builder()
                .setSubject(user.get().getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS256,
                        SECRET_KEY.getBytes()).compact();
    }
}
