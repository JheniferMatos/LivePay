package br.solutis.livepay;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service1", r -> r
                        .path("/auth/cadastrar")
                        .filters(f -> f
                                .modifyRequestBody(String.class, String.class, (exchange, s) -> {

                                    String parametro1 = exchange.getRequest().getQueryParams().getFirst("parametro1");
                                    String parametro2 = exchange.getRequest().getQueryParams().getFirst("parametro2");


                                    return Mono.just(parametro1 + parametro2);
                                })
                        )
                        .uri("http://localhost:8081")
                ).build();
    }
}
