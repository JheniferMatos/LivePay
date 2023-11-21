package com.br.livepaypag.dto;

import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.InformacaoPedido;
import com.br.livepaypag.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * {@code LerPagamentoDTO} é uma classe de transferência de dados (DTO) que representa as informações de leitura de um pagamento.
 * Utilizada para comunicar informações de pagamento entre as camadas de controle e serviço.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LerPagamentoDTO {

    /**
     * ID do pagamento.
     */
    private Long id;

    /**
     * Informações do pedido associado ao pagamento.
     */
    private InformacaoPedido valor;

    /**
     * Nome do cliente.
     */
    private String nome;

    /**
     * Endereço de e-mail do cliente.
     */
    private String email;

    /**
     * Cartão utilizado para o pagamento.
     */
    private Cartao cartao;

    /**
     * Status do pagamento.
     */
    private Status status;
}
