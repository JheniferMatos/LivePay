package com.br.livepaypag.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private Long userId;

    private String email;

    private String assunto;

    private String texto;


}
