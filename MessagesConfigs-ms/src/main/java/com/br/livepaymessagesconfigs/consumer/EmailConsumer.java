package com.br.livepaymessagesconfigs.consumer;

import com.br.livepaymessagesconfigs.dto.EmailDto;
import com.br.livepaymessagesconfigs.model.Email;
import com.br.livepaymessagesconfigs.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Consumidor de mensagens para o envio de e-mails.
 */
@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    /**
     * Método que recebe mensagens da fila e envia e-mails.
     *
     * @param emailDto Objeto DTO contendo informações do e-mail
     */
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveMessages(@Payload EmailDto emailDto) {
        var emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendEmail(emailModel);

    }
}
