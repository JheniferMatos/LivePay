package br.com.caboumony.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * Serviço para validação de tokens JWT.
 */
@Component
public class JwtService {

    /**
     * Chave secreta para validação do token JWT.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Valida um token JWT.
     *
     * @param token Token JWT a ser validado.
     * @return true se o token for válido, false se não for válido.
     */
    public boolean validarToken(String token) {
        try {
            // Configura o parser do JWT com a chave secreta
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    // Tenta fazer o parse do token e obter as claims
                    .parseClaimsJws(token)
                    .getBody();
            return true; // Se o parse for bem-sucedido, o token é válido
        } catch (Exception e) {
            return false; // Se ocorrer uma exceção, o token não é válido
        }
    }
}
