package com.br.livepaypag.service;

import com.br.livepaypag.dto.CartaoDto;
import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.InformacaoPedido;
import com.br.livepaypag.model.Pagamento;
import com.br.livepaypag.repository.InformacoesPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformacoesPedidoService {

    @Autowired
    private InformacoesPedidoRepository informacoesPedidoRepository;

    public void processarPagamento(PagamentoDTO pagamentoDTO){

        InformacaoPedido informacoesPedido = new InformacaoPedido();
        informacoesPedido.setId(pagamentoDTO.getId());
        informacoesPedido.setValor(pagamentoDTO.getValor());

        // Salva no banco de dados
        informacoesPedidoRepository.save(informacoesPedido);

    }

}
