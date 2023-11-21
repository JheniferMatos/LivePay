package com.br.livepaypedidos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO utilizado para criar um novo pedido.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarPedidoDTO {

    /**
     * O ID do pedido.
     */
    private Long id;

    /**
     * O total do pedido.
     */
    private Double total;

    /**
     * O ID da pessoa associada ao pedido.
     */
    private Long pessoa_id;

    /**
     * Lista de IDs dos produtos associados ao pedido.
     */
    @NotNull
    private List<Long> produtos_id;

}
