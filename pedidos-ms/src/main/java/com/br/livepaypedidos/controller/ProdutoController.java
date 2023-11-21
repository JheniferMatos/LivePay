package com.br.livepaypedidos.controller;

import com.br.livepaypedidos.dto.ProdutoDTO;
import com.br.livepaypedidos.service.ProdutoService;
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

/**
 * Controller responsável por gerenciar os endpoints relacionados aos Produtos do Microsserviço.
 */
@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints para Gerenciamento de Produtos do Microsservice de Pedidos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Recupera uma página de Produtos.
     *
     * @param paginacao O objeto Pageable que define as opções de paginação.
     * @return Uma página de {@link ProdutoDTO}.
     */
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Encontrar todos os Produtos", description = "Encontrar todos os Produtos",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ProdutoDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public Page<ProdutoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return produtoService.obterTodos(paginacao);
    }

    /**
     * Recupera um Produto com base no ID fornecido.
     *
     * @param id O ID do Produto a ser recuperado.
     * @return Um {@link ResponseEntity} contendo o {@link ProdutoDTO} correspondente.
     */
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Encontrar um Produto", description = "Encontrar um Produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<ProdutoDTO> detalhar(@PathVariable @NotNull Long id) {
        ProdutoDTO dto = produtoService.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Cria um novo Produto com base nos dados fornecidos no corpo da requisição.
     *
     * @param dto O objeto {@link ProdutoDTO} que representa os dados do novo Produto.
     * @return Um {@link ResponseEntity} contendo o {@link ProdutoDTO} do Produto recém-criado.
     */
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Adicionar um novo Produto",
            description = "Adiciona um novo Produto passando uma representação JSON do Produto!",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO produto = produtoService.criarProduto(dto);
        return ResponseEntity.ok(produto);
    }

    /**
     * Atualiza um Produto existente com base no ID fornecido e nos dados no corpo da requisição.
     *
     * @param id O ID do Produto a ser atualizado.
     * @param dto O objeto {@link ProdutoDTO} que representa os novos dados do Produto.
     * @return Um {@link ResponseEntity} contendo o {@link ProdutoDTO} atualizado.
     */
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Atualizar um Produto",
            description = "Atualiza um Produto passando uma representação JSON ou XML do produto!",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Remove um Produto com base no ID fornecido.
     *
     * @param id O ID do Produto a ser removido.
     * @return Um {@link ResponseEntity} indicando o sucesso da remoção.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um Produto",
            description = "Deleta um Produto passando um Id que representa um Produto!",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<ProdutoDTO> remover(@PathVariable @NotNull Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
