package com.br.livepaypag.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper novoModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }



}
