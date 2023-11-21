package com.br.livepaypedidos.producer;

import com.br.livepaypedidos.dto.PagamentoDto;
import com.br.livepaypedidos.model.Pedidos;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por publicar mensagens relacionadas a pedidos.
 */
@Component
public class PedidoProducer {

    /**
     * O template do RabbitMQ para enviar mensagens.
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * A chave de roteamento para a fila do RabbitMQ.
     */
    @Value(value = "${broker.queue.pedido.name}")
    private String routingKey;

    /**
     * Publica uma mensagem de pagamento relacionada a um pedido.
     *
     * @param pedidos O pedido para o qual a mensagem de pagamento está relacionada.
     */
    public void publishMessagePagamento(Pedidos pedidos) {
        var pagamentoDto = new PagamentoDto();

        pagamentoDto.setId(pedidos.getId());
        pagamentoDto.setValor(pedidos.getTotal());

        rabbitTemplate.convertAndSend("", routingKey, pagamentoDto);
    }
}
