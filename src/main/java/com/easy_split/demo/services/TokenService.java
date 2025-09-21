package com.easy_split.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.enums.Role;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("auth-easy-split-api") // who created token
                    .withSubject(user.getEmail())
                    .withIssuedAt(new Date())
                    .withExpiresAt(DateUtils.addDaysToDate(new Date(), 1))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error generation token for user", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("auth-easy-split-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Token invalid or expired", exception);
        }
    }
}
