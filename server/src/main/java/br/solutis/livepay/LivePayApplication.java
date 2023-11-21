package br.solutis.livepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * {@code LivePayApplication} é a classe principal para o microserviço LivePay,
 * iniciando o servidor Eureka.
 * Utiliza a anotação {@code @SpringBootApplication} para configurar a classe principal
 * e automaticamente habilitar as configurações padrão do Spring Boot.
 * Além disso, utiliza a anotação {@code @EnableEurekaServer} para habilitar o servidor Eureka.
 *
 * O método {@code main} serve como ponto de entrada da aplicação,
 * onde o Spring Boot inicia a execução.
 */
@SpringBootApplication
@EnableEurekaServer
public class LivePayApplication {

    /**
     * O método {@code main} inicia o servidor Eureka para o microserviço LivePay.
     *
     * @param args Argumentos de linha de comando passados para a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(LivePayApplication.class, args);
    }
}
