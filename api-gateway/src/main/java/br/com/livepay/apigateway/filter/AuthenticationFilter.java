package br.com.livepay.apigateway.filter;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    @Autowired
    private RouterValidator routerValidator;

    public AuthenticationFilter () {
        super(NameConfig.class);
    }
    @Override
    public GatewayFilter apply(NameConfig config) {
        return ((exchange, chain) -> {
            //header contem token ou nao
            if(routerValidator.isSecured.test(exchange.getRequest())){

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing header authorization");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                }

//                try {
//                    //validar Token
//                }

            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
