package com.br.livepaypag.consumer;

import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.model.InformacaoPedido;
import com.br.livepaypag.model.Pagamento;
import com.br.livepaypag.repository.InformacoesPedidoRepository;
import com.br.livepaypag.service.InformacoesPedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    @Autowired
    InformacoesPedidoRepository informacoesPedidoRepository;

    @Autowired
    InformacoesPedidoService informacoesPedidoService;

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void receiveMessages(@Payload PagamentoDTO pagamentoDTO){

        informacoesPedidoService.processarPagamento(pagamentoDTO);
    }

}
