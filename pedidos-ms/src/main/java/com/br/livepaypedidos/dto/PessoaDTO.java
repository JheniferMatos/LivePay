package com.br.livepaypedidos.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

}
