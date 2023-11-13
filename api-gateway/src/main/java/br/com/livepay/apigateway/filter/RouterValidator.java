package br.com.livepay.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouterValidator {


    public static final List<String> openEndpoints = List.of(
            "/auth/cadastrar",
            "/auth/login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
