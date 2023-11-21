package com.br.livepaypedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa um pedido.
 */
@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedidos {

    /**
     * O ID do pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O total do pedido.
     */
    @Column
    private Double total;

    /**
     * A pessoa associada ao pedido.
     */
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    /**
     * Lista de produtos associados ao pedido.
     */
    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    @NotNull
    private List<Produto> produto = new ArrayList<>();
}
