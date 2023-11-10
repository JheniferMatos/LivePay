package com.br.livepaypag.dto;

import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.Status;
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

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
        private Long id;

        private BigDecimal valor;

        private String nome;

        private String email;

        private Long cartao_id;

        private Status status;

}
