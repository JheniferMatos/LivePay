package br.com.livepay.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Classe que valida os endpoints abertos e protegidos.
 */
@Component
public class RouterValidator {

        /**
         * Lista de endpoints abertos que não requerem autenticação.
         */
        public static final List<String> openEndpoints = List.of(
                        "/auth/cadastrar",
                        "/auth/login",
                        "/auth/validar",
                        "/eureka",
                        "/auth/**",
                        "ms-security/**");

        /**
         * Predicado para verificar se a requisição é para um endpoint protegido.
         */
        public Predicate<ServerHttpRequest> isSecured = request -> openEndpoints
                        .stream()
                        .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
