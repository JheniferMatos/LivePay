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

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public Email sendEmail(Email email){
        try {
            email.setEnviarDataEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmail());
            message.setSubject(email.getAssunto());
            message.setText(email.getTexto());
            mailSender.send(message);

            email.setStatusEmail(StatusEmail.ENVIADO);
        }  catch (MailException e){
            email.setStatusEmail(StatusEmail.ERRO);
        } finally {
            return emailRepository.save(email);
        }
    }

}
