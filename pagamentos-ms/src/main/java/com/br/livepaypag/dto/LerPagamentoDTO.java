package com.br.livepaypag.dto;

import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.Status;
import com.br.livepaypag.model.TipoDePagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LerPagamentoDTO {

    private Long id;

    private BigDecimal valor;

    private String nome;

    private String email;

    private Cartao cartao;

    private Status status;
}
