package com.br.livepaymessagesconfigs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para o Microsserviço de Mensagens.
 */
@Configuration
public class RabbitMqConfig {

    @Value("${broker.queue.email.name}")
    private String queue;

    /**
     * Define a fila no RabbitMQ.
     *
     * @return Objeto Queue configurado
     */
    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    /**
     * Configura o conversor de mensagens para JSON.
     *
     * @return Jackson2JsonMessageConverter configurado
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
