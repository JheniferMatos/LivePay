package com.br.livepaypag.service;

import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.model.Pagamento;
import com.br.livepaypag.model.Status;
import com.br.livepaypag.producers.PagamentoProducer;
import com.br.livepaypag.repository.CartaoRepository;
import com.br.livepaypag.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private PagamentoProducer pagamentoProducer;

    @Autowired
    private ModelMapper modelMapper;

    public Page<LerPagamentoDTO> obterTodos(Pageable pageable){
        return pagamentoRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, LerPagamentoDTO.class));
    }

    public LerPagamentoDTO obterPorId(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, LerPagamentoDTO.class);
    }

    @Transactional
    public LerPagamentoDTO criarPagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamento.setCartao(cartaoRepository.findById(pagamentoDTO.getCartao_id())
                .orElseThrow(RuntimeException::new));

        pagamento = pagamentoRepository.save(pagamento);
        pagamentoProducer.publishMessageEmail(pagamento);

        return modelMapper.map(pagamento, LerPagamentoDTO.class);
    }

    public void confirmarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        pagamento.setStatus(Status.CONFIRMADO);

    }

    public void excluirPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                        .orElseThrow(RuntimeException::new);
        pagamento.setStatus(Status.CANCELADO);

    }

}
