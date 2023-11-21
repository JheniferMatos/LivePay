package com.br.livepaypedidos.dto;

import com.br.livepaypedidos.model.Pessoa;
import com.br.livepaypedidos.model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO utilizado para representar os detalhes de um pedido.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LerPedidoDTO {

    /**
     * O ID do pedido.
     */
    private Long id;

    /**
     * O total do pedido.
     */
    private Double total;

    /**
     * A pessoa associada ao pedido.
     */
    private Pessoa pessoa;

    /**
     * Lista de produtos associados ao pedido.
     */
    private List<Produto> produto;

}
