package br.com.caboumony.security.security;

import br.com.caboumony.security.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço para manipulação de tokens JWT.
 */
@Service
public class TokenService {
    
    /**
     * Chave secreta para assinatura do token JWT.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Gera um token JWT para o usuário fornecido.
     *
     * @param usuario Usuário para o qual o token será gerado.
     * @return Token JWT gerado.
     * @throws JWTCreationException Se ocorrer um erro durante a criação do token JWT.
     */
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("MS Security")
                    .withClaim("id", usuario.getId())
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar token JWT", exception);
        }
    }

    /**
     * Obtém o subject (usuário) do token JWT fornecido.
     *
     * @param tokenJWT Token JWT a ser verificado.
     * @return Subject (usuário) do token JWT.
     * @throws RuntimeException Se o token JWT for inválido ou expirado.
     */
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("MS Security")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    /**
     * Calcula a data de expiração para o token JWT (2 horas a partir do momento atual).
     *
     * @return Instant representando a data de expiração.
     */
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
