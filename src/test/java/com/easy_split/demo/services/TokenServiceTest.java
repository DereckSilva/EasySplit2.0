package com.easy_split.demo.services;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        tokenService = mock(TokenService.class);
    }

    @Test
    @DisplayName("Esse método retorna um token para o usuário")
    void generateToken() {
        User user = new User(1, "teste@gmail.com", "12345", Role.USER);
        String tokenCriado = retornaToken("token-criado");
        when(tokenService.generateToken(user)).thenReturn(tokenCriado);

        String token = tokenService.generateToken(user);
        assertEquals(tokenCriado, token);
    }

    @Test
    @DisplayName("Esse método verifica se o token informado para o usuário é válido")
    void getSubjectFromToken() {
        when(tokenService.getSubjectFromToken(retornaToken("ola-mundo"))).thenReturn("token-usuario-requisicao");
        String tokenValid = tokenService.getSubjectFromToken(retornaToken("ola-mundo"));
        assertEquals("token-usuario-requisicao", tokenValid);
    }

    @Test
    @DisplayName("Esse método retorna uma exception de token expirado ou invalido")
    void getSubjectFromTokenException() {
        when(tokenService.getSubjectFromToken(retornaToken("token-expirado"))).thenThrow(new JWTCreationException("Token expirado ou inválido", new Exception()));
        try {
            tokenService.getSubjectFromToken(retornaToken("token-expirado"));
        } catch (JWTCreationException e) {
            assertEquals("Token expirado ou inválido", e.getMessage());
        }
    }

    private String retornaToken (String token) {
        byte[] tokenBytes = token.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(tokenBytes).toString();
    }
}