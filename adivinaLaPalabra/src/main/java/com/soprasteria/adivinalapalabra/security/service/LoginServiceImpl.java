package com.soprasteria.adivinalapalabra.security.service;

import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.jwt.JWTProvider;
import com.soprasteria.adivinalapalabra.security.jwt.JWTTokenFilter;
import com.soprasteria.adivinalapalabra.security.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> login(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }

    public UserEntity getUserFromToken(
            HttpServletRequest httpServletRequest
    ) throws UsernameNotFoundException {
        String header = httpServletRequest.getHeader("Authorization");
        String token = header.replace("Bearer ", "");
        String name = Jwts.parserBuilder()
                        .setSigningKey(getSecret(secret))
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

        return userRepository.findByName(name).get();
    }

    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);

        return Keys.hmacShaKeyFor(secretBytes);
    }
}
