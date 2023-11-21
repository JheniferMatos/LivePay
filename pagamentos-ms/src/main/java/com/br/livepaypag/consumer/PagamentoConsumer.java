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

/**
 * {@code PagamentoConsumer} é uma classe responsável por consumir mensagens relacionadas a pagamentos
 * provenientes de uma fila RabbitMQ no pacote com.br.livepaypag.consumer.
 * Utiliza a anotação {@code @Component} para indicar que é um componente gerenciado pelo Spring.
 */
@Component
public class PagamentoConsumer {

    /**
     * Repositório para manipulação de informações de pedido.
     */
    @Autowired
    InformacoesPedidoRepository informacoesPedidoRepository;

    /**
     * Serviço para processamento de informações de pedido.
     */
    @Autowired
    InformacoesPedidoService informacoesPedidoService;

    /**
     * Método anotado com {@code @RabbitListener} que recebe mensagens da fila de pagamento RabbitMQ.
     *
     * @param pagamentoDTO Objeto contendo as informações do pagamento recebido da fila.
     */
    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void receiveMessages(@Payload PagamentoDTO pagamentoDTO){
        informacoesPedidoService.processarPagamento(pagamentoDTO);
    }

}
