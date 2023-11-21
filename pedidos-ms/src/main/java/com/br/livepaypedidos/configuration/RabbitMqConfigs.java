package com.br.livepaypedidos.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ.
 */
@Configuration
public class RabbitMqConfigs {

    /**
     * Configura um conversor de mensagem JSON para objetos Java utilizando o Jackson.
     *
     * @return Uma instância de Jackson2JsonMessageConverter configurada.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
