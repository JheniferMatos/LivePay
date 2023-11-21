package com.br.livepaypag.controller;

import com.br.livepaypag.dto.CartaoDto;
import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.service.CartaoService;
import com.br.livepaypag.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * {@code PagamentoController} é uma classe responsável por lidar com as
 * requisições relacionadas a pagamentos
 * no Microsserviço de Pagamentos.
 * Utiliza a anotação {@code @RestController} para indicar que é um controlador
 * REST.
 * O mapeamento de URL padrão para os endpoints desta classe é "/pagamentos".
 */
@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento", description = "Endpoints para Gerenciamento de Pagamentos do Microsservice de Pagamentos")
public class PagamentoController {

        /**
         * Serviço para gerenciamento de pagamentos.
         */
        @Autowired
        private PagamentoService pagamentoService;

        /**
         * Serviço para gerenciamento de cartões.
         */
        @Autowired
        private CartaoService cartaoService;

        /**
         * Método para listar todos os pagamentos paginados.
         * Mapeado para a URL "/pagamentos" com o método HTTP GET.
         *
         * @param paginacao Objeto contendo informações de paginação.
         * @return Página de pagamentos paginados.
         */
        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Encontrar todos os Pagamentos", description = "Encontrar todos os Pagamentos", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LerPagamentoDTO.class)))
                                        }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public Page<LerPagamentoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
                return pagamentoService.obterTodos(paginacao);
        }

        /**
         * Método para detalhar um pagamento pelo ID.
         * Mapeado para a URL "/pagamentos/{id}" com o método HTTP GET.
         *
         * @param id ID do pagamento a ser detalhado.
         * @return Resposta HTTP contendo o detalhe do pagamento.
         */
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Encontrar um Pagamento", description = "Encontrar um Pagamento", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = LerPagamentoDTO.class))),
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<LerPagamentoDTO> detalhar(@PathVariable @NotNull Long id) {
                LerPagamentoDTO dto = pagamentoService.obterPorId(id);
                return ResponseEntity.ok(dto);
        }

        /**
         * Método para cadastrar um novo pagamento.
         * Mapeado para a URL "/pagamentos" com o método HTTP POST.
         *
         * @param dto Objeto contendo as informações do pagamento a ser cadastrado.
         * @return Resposta HTTP contendo o pagamento cadastrado.
         */
        @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
                        MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adicionar um novo Pagamento", description = "Adiciona um novo Pagamento passando uma representação JSON do Pagamento!", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PagamentoDTO.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<LerPagamentoDTO> cadastrar(@RequestBody @Valid PagamentoDTO dto) {
                LerPagamentoDTO pagamento = pagamentoService.criarPagamento(dto);
                return ResponseEntity.ok(pagamento);
        }

        /**
         * Método para confirmar um pagamento pelo ID.
         * Mapeado para a URL "/pagamentos/{id}" com o método HTTP POST.
         *
         * @param id ID do pagamento a ser confirmado.
         * @return Resposta HTTP indicando a confirmação do pagamento.
         */
        @PostMapping(value = "/{id}")
        @Operation(summary = "Confirma um Pagamento", description = "Confirma um Pagamento passando um Id de Pagamento", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<PagamentoDTO> confirmarPagamento(@PathVariable @NotNull Long id) {
                pagamentoService.confirmarPagamento(id);
                return ResponseEntity.noContent().build();
        }

        /**
         * Método para criar um novo cartão de pagamento.
         * Mapeado para a URL "/pagamentos/cartao" com o método HTTP POST.
         *
         * @param dto Objeto contendo as informações do cartão a ser cadastrado.
         * @return Resposta HTTP contendo o cartão de pagamento cadastrado.
         */
        @PostMapping(value = "/cartao", consumes = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adicionar um novo Cartao", description = "Adiciona um novo Cartao passando uma representação JSON do Cartao!", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CartaoDto.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<CartaoDto> criarCartao(@RequestBody CartaoDto dto) {
                CartaoDto cartaoDto = cartaoService.criarPagamento(dto);
                return ResponseEntity.ok(cartaoDto);
        }

        /**
         * Método para remover um pagamento pelo ID.
         * Mapeado para a URL "/pagamentos/{id}" com o método HTTP DELETE.
         *
         * @param id ID do pagamento a ser removido.
         * @return Resposta HTTP indicando a remoção do pagamento.
         */
        @DeleteMapping("/{id}")
        @Operation(summary = "Deletar um Pagamento", description = "Deleta um Pagamento passando um Id que representa um Pagamento!", tags = {
                        "Pagamento" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id) {
                pagamentoService.excluirPagamento(id);
                return ResponseEntity.noContent().build();
        }
}
