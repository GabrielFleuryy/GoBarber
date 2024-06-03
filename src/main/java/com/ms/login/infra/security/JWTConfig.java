package com.ms.login.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ms.login.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JWTConfig {

    @Value("${api.login.secret.key}")
    private String secretKey;

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("API-LOGIN")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getUserId())
                    .withExpiresAt(expiredDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating token", exception);
        }
    }

    private Instant expiredDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-00:00"));
    }
}
