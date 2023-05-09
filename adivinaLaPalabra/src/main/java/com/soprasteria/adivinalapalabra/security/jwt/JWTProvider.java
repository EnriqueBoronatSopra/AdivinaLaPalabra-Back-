package com.soprasteria.adivinalapalabra.security.jwt;

import com.soprasteria.adivinalapalabra.security.entity.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTProvider {

    private final static Logger logger = LoggerFactory.getLogger(JWTProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        Date createDate = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .signWith(getSecret(secret))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecret(secret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecret(secret))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("malformed token");
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("expired token");
        } catch (IllegalArgumentException e) {
            logger.error("empty token");
        } catch (SignatureException e) {
            logger.error("fail with signature");
        }
        return false;
    }

    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(secretBytes);
    }
}
