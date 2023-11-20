package com.br.livepaypedidos.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper novoModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API with Java 20 and Spring Boot 3")
                        .version("v1")
                        .description("Cabou o Money")
                        .termsOfService("https://cabouomoney.com.br")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://cabouomoney.com.br")));
    }

}
