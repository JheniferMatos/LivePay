package com.br.livepaymessagesconfigs.service;

import com.br.livepaymessagesconfigs.model.Email;
import com.br.livepaymessagesconfigs.model.StatusEmail;
import com.br.livepaymessagesconfigs.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Serviço para envio de e-mails.
 */
@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    /**
     * Envia um e-mail e atualiza o status no banco de dados.
     *
     * @param email O objeto {@link Email} com as informações do e-mail.
     * @return O objeto {@link Email} com o status atualizado.
     */
    public Email sendEmail(Email email) {
        try {
            email.setEnviarDataEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmail());
            message.setSubject(email.getAssunto());
            message.setText(email.getTexto());
            mailSender.send(message);

            email.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERRO);
        } finally {
            return emailRepository.save(email);
        }
    }

}
