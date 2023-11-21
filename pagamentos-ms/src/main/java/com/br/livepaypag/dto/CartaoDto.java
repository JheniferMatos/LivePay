package com.br.livepaypag.dto;

import com.br.livepaypag.model.TipoDePagamento;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code CartaoDto} é uma classe de transferência de dados (DTO) que representa as informações de um cartão de pagamento.
 * Utilizada para comunicação entre as camadas de controle e serviço.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDto {

    /**
     * ID do cartão.
     */
    private Long id;

    /**
     * Nome do titular do cartão.
     */
    private String nome;

    /**
     * Número do cartão.
     */
    private String numero;

    /**
     * Data de expiração do cartão.
     */
    private String dataExpiracao;

    /**
     * Código de segurança do cartão.
     */
    private String codigoSeguranca;

    /**
     * Tipo de pagamento associado ao cartão.
     */
    private TipoDePagamento tipoDePagamento;
}
