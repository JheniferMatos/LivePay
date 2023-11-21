package com.br.livepaypedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa uma pessoa.
 */
@Entity
@Table(name = "pessoas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    /**
     * O ID da pessoa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome da pessoa.
     */
    @Column
    private String nome;

    /**
     * O email da pessoa.
     */
    @Column
    private String email;

    /**
     * O CPF da pessoa.
     */
    @Column
    private String cpf;
}
