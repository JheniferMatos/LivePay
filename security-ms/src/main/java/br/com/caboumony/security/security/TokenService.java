package br.com.caboumony.security.security;

import br.com.caboumony.security.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("MS Security")
                    .withClaim("id", usuario.getId())
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("erro ao gerar token jwt", exception);
        }
    }
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256("123456");
            return JWT.require(algoritmo)
                    .withIssuer("MS Security")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }


    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
