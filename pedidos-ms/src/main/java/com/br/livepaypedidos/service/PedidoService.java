package com.br.livepaypedidos.service;

import com.br.livepaypedidos.dto.CriarPedidoDTO;
import com.br.livepaypedidos.dto.LerPedidoDTO;
import com.br.livepaypedidos.exceptions.RequiredObjectIsNullException;
import com.br.livepaypedidos.exceptions.ResourceNotFoundException;
import com.br.livepaypedidos.model.Pedidos;
import com.br.livepaypedidos.model.Produto;
import com.br.livepaypedidos.producer.PedidoProducer;
import com.br.livepaypedidos.repository.PedidoRepository;
import com.br.livepaypedidos.repository.PessoaRepository;
import com.br.livepaypedidos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas a pedidos.
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PedidoProducer pedidoProducer;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtém todos os pedidos paginados.
     *
     * @param pageable A configuração da página.
     * @return Página de pedidos no formato DTO.
     */
    public Page<LerPedidoDTO> obterTodos(Pageable pageable){
        return pedidoRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, LerPedidoDTO.class));
    }

    /**
     * Obtém um pedido por ID.
     *
     * @param id O ID do pedido.
     * @return DTO representando o pedido.
     * @throws EntityNotFoundException Se o pedido não for encontrado.
     */
    public LerPedidoDTO obterPorId(Long id){
        Pedidos pedidos = pedidoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pedidos, LerPedidoDTO.class);
    }

    /**
     * Cria um novo pedido.
     *
     * @param pedidoDTO DTO contendo os detalhes do pedido.
     * @return DTO representando o pedido criado.
     * @throws RequiredObjectIsNullException Se o pedidoDTO for nulo.
     * @throws ResourceNotFoundException Se o recurso não for encontrado (pessoa ou produto).
     */
    public LerPedidoDTO criarPedido(CriarPedidoDTO pedidoDTO){

        if (pedidoDTO == null){throw new RequiredObjectIsNullException();}

        Pedidos pedidoCriado = modelMapper.map(pedidoDTO, Pedidos.class);
        pedidoCriado.setProduto(
                pedidoDTO.getProdutos_id()
                        .stream()
                        .map(idProduto -> produtoRepository.findById(idProduto)
                                .orElseThrow(ResourceNotFoundException::new))
                        .collect(Collectors.toList())
        );
        pedidoCriado.setPessoa(pessoaRepository.findById(pedidoDTO.getPessoa_id())
                .orElseThrow(ResourceNotFoundException::new));

        pedidoCriado.setTotal(calcularValorTotal(pedidoCriado));

        pedidoCriado = pedidoRepository.save(pedidoCriado);
        pedidoProducer.publishMessagePagamento(pedidoCriado);

        return modelMapper.map(pedidoCriado, LerPedidoDTO.class);

    }

    /**
     * Calcula o valor total de um pedido com base nos produtos.
     *
     * @param pedido O pedido para o qual calcular o valor total.
     * @return O valor total do pedido.
     */
    public double calcularValorTotal(Pedidos pedido) {
        double valorTotal = 0.0;

        if (pedido.getProduto() != null) {
            for (Produto produto : pedido.getProduto()) {
                valorTotal += produto.getValor();
            }
        }

        return valorTotal;
    }
}
