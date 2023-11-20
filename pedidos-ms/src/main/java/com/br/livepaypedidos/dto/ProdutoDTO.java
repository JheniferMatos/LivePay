package com.br.livepaypedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;

    private String nome;

    private BigDecimal valor;

    //private Integer quantidade;

    //private Integer quantidadeNoCarrinho;

}
