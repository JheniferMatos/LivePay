package com.br.livepaypag.controller;

import com.br.livepaypag.dto.CartaoDto;
import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.service.CartaoService;
import com.br.livepaypag.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public Page<LerPagamentoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return pagamentoService.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LerPagamentoDTO> detalhar(@PathVariable @NotNull Long id) {
        LerPagamentoDTO dto = pagamentoService.obterPorId(id);

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<LerPagamentoDTO> cadastrar(@RequestBody @Valid PagamentoDTO dto) {
        LerPagamentoDTO pagamento = pagamentoService.criarPagamento(dto);

        return ResponseEntity.ok(pagamento);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PagamentoDTO> confirmarPagamento(@PathVariable @NotNull Long id) {
        pagamentoService.confirmarPagamento(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/cartao")
    public ResponseEntity<CartaoDto> criarCartao(@RequestBody CartaoDto dto) {
        CartaoDto cartaoDto = cartaoService.criarPagamento(dto);
        return ResponseEntity.ok(cartaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
