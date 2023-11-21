package com.br.livepaypag.dto;

import com.br.livepaypag.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@code PagamentoDTO} é uma classe de transferência de dados (DTO) que
 * representa as informações para criação de um pagamento.
 * Utilizada para comunicar informações de pagamento entre as camadas de
 * controle e serviço.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

        /**
         * ID do pagamento.
         */
        private Long id;

        /**
         * Valor do pagamento.
         */
        private Double valor;

        /**
         * ID da informação do pedido associado ao pagamento.
         */
        private Long valor_id;

        /**
         * Nome do cliente.
         */
        private String nome;

        /**
         * Endereço de e-mail do cliente.
         */
        private String email;

        /**
         * ID do cartão utilizado para o pagamento.
         */
        private Long cartao_id;

        /**
         * Status do pagamento.
         */
        private Status status;

}
