package br.com.caboumony.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * A classe {@code SecurityApplication} é a classe principal do aplicativo Spring Boot
 * responsável por iniciar o microsserviço de segurança.
 *
 * <p>
 * O método {@code main} é o ponto de entrada do aplicativo, onde o Spring Boot inicia a execução.
 * </p>
 *
 * @see SpringBootApplication
 * @see EnableDiscoveryClient
 */
@SpringBootApplication
public class SecurityApplication {

    /**
     * O método {@code main} inicia a execução do aplicativo Spring Boot.
     *
     * @param args Argumentos de linha de comando passados para o aplicativo.
     */
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
