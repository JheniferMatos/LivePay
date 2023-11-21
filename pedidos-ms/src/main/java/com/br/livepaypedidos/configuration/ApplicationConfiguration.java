package com.br.livepaypedidos.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração da aplicação.
 */
@Configuration
public class ApplicationConfiguration {

    /**
     * Configuração do ModelMapper.
     *
     * @return Uma instância de ModelMapper configurada.
     */
    @Bean
    public ModelMapper novoModelMapper() {
        return new ModelMapper();
    }

    /**
     * Configuração personalizada para a documentação Swagger (OpenAPI).
     *
     * @return Uma instância de OpenAPI configurada.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API with Java 20 and Spring Boot 3")
                        .version("v1")
                        .description("Cabou o Money")
                        .termsOfService("https://cabouomoney.com.br")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://cabouomoney.com.br")));
    }

}
