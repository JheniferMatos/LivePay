package br.com.livepay.apigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe utilitária para operações relacionadas a tokens JWT.
 */
@Component
public class JwtUtil {

    /**
     * Chave secreta para assinatura e verificação do token.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Valida um token JWT.
     *
     * @param token Token JWT a ser validado
     * @return true se o token for válido, false se não for
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar o token JWT");
        }
    }
}
