package com.br.livepaypedidos.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO utilizado para representar informações de uma pessoa.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    /**
     * O ID da pessoa.
     */
    private Long id;

    /**
     * O nome da pessoa.
     */
    private String nome;

    /**
     * O email da pessoa.
     */
    private String email;

    /**
     * O CPF da pessoa.
     */
    private String cpf;

}
