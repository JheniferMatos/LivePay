package com.br.livepaypag.producers;

import com.br.livepaypag.dto.EmailDto;
import com.br.livepaypag.model.Pagamento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por enviar mensagens relacionadas a pagamentos para uma fila RabbitMQ.
 */
@Component
public class PagamentoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    /**
     * Publica uma mensagem de e-mail para a fila RabbitMQ.
     *
     * @param pagamento O pagamento associado à mensagem de e-mail.
     */
    public void publishMessageEmail(Pagamento pagamento) {
        var emailDto = new EmailDto();

        emailDto.setUserId(pagamento.getId());
        emailDto.setEmail(pagamento.getEmail());
        emailDto.setAssunto("Pagamento Efetuado");
        emailDto.setTexto("Seu pagamento foi efetuado " + pagamento.getNome() + " no valor de: " + pagamento.getValor());

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
