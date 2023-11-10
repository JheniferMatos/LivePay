package com.br.livepaypag.service;

import com.br.livepaypag.dto.CartaoDto;
import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.exceptions.ResourceNotFoundException;
import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.Pagamento;
import com.br.livepaypag.model.Status;
import com.br.livepaypag.repository.CartaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<CartaoDto> obterTodos(Pageable pageable){
        return cartaoRepository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, CartaoDto.class));
    }

    public CartaoDto obterPorId(Long id){
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return modelMapper.map(cartao, CartaoDto.class);
    }

    @Transactional
    public CartaoDto criarPagamento(CartaoDto cartaoDto){
        Cartao cartao = modelMapper.map(cartaoDto, Cartao.class);
        cartao = cartaoRepository.save(cartao);
        return modelMapper.map(cartao, CartaoDto.class);
    }

}
