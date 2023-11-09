package com.br.livepaypedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private BigDecimal valor;

    //private Integer quantidade;

    //private Integer quantidadeNoCarrinho;

}
