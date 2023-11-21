package br.com.livepay.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal para iniciar a aplicação do Gateway da API.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * Método principal para iniciar a aplicação.
     *
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
