package com.br.livepaypag.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code RabbitMqConfigs} é uma classe de configuração que define beans relacionados
 * à configuração do RabbitMQ no pacote com.br.livepaypag.configurations.
 * Utiliza a anotação {@code @Configuration} para indicar que é uma classe de configuração do Spring.
 */
@Configuration
public class RabbitMqConfigs {

    /**
     * Obtém o nome da fila do arquivo de propriedades.
     */
    @Value("${broker.queue.pedido.name}")
    private String queue;

    /**
     * Cria e retorna uma instância de {@code Queue} para configuração da fila RabbitMQ.
     *
     * @return Uma instância de {@code Queue} configurada.
     */
    @Bean
    public Queue queue(){
        return new Queue(queue, true);
    }

    /**
     * Cria e retorna uma instância de {@code Jackson2JsonMessageConverter} para a conversão de mensagens
     * para o formato JSON ao interagir com o RabbitMQ.
     *
     * @return Uma instância de {@code Jackson2JsonMessageConverter} configurada.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
