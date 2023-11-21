package com.br.livepaymessagesconfigs.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para representar informações de e-mail.
 */
@Getter
@Setter
public class EmailDto {

    private Long userId;

    private String email;

    private String assunto;

    private String texto;
}
