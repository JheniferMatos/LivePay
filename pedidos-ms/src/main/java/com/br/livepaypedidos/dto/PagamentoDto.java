package com.br.livepaypedidos.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO utilizado para representar informações de pagamento.
 */
@Getter
@Setter
public class PagamentoDto {

    /**
     * O ID do pagamento.
     */
    private Long id;

    /**
     * O valor do pagamento.
     */
    private Double valor;

}
