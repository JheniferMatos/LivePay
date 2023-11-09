package com.br.livepaypag.dto;

import com.br.livepaypag.model.TipoDePagamento;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoDto {

    private Long id;

    private String nome;

    private String numero;


    private String dataExpiracao;


    private String codigoSeguranca;

    private TipoDePagamento tipoDePagamento;


}
