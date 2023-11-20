package com.br.livepaypedidos.dto;

import com.br.livepaypedidos.model.Pessoa;
import com.br.livepaypedidos.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LerPedidoDTO {

    private Long id;

    private Double total;

    private Pessoa pessoa;

    private List<Produto> produto;

}
