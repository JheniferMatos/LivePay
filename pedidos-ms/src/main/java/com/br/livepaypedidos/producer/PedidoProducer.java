package com.br.livepaypedidos.producer;

import com.br.livepaypedidos.dto.PagamentoDto;
import com.br.livepaypedidos.model.Pedidos;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Value(value = "${broker.queue.pedido.name}")
    private String routingKey;

    public void publishMessagePagamento(Pedidos pedidos) {
        var pagamentoDto = new PagamentoDto();

        pagamentoDto.setId(pedidos.getId());
        pagamentoDto.setValor(pedidos.getTotal());

        rabbitTemplate.convertAndSend("", routingKey, pagamentoDto);
    }


}
