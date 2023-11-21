package com.br.livepaypag.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * {@code EmailDto} é uma classe de transferência de dados (DTO) que representa as informações de um e-mail.
 * Utilizada para comunicação entre as camadas de controle e serviço.
 */
@Getter
@Setter
public class EmailDto {

    /**
     * ID do usuário associado ao e-mail.
     */
    private Long userId;

    /**
     * Endereço de e-mail.
     */
    private String email;

    /**
     * Assunto do e-mail.
     */
    private String assunto;

    /**
     * Texto do e-mail.
     */
    private String texto;
}
