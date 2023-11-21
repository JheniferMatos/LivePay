package com.br.livepaypedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa um produto.
 */
@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    /**
     * O ID do produto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do produto.
     */
    @Column
    @NotBlank
    private String nome;

    /**
     * O valor do produto.
     */
    @Column
    @NotNull
    private Double valor;

    

    //    /**
    //     * A quantidade do produto.
    //     */
    //    @Column
    //    @NotNull
    //    private Integer quantidade;
    //
    //    /**
    //     * A quantidade no carrinho do produto.
    //     */
    //    @Column
    //    private Integer quantidadeNoCarrinho;
}
