package com.br.livepaypedidos.controller;

import com.br.livepaypedidos.dto.PessoaDTO;
import com.br.livepaypedidos.dto.ProdutoDTO;
import com.br.livepaypedidos.model.Pessoa;
import com.br.livepaypedidos.service.PessoaService;
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

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Endpoints para Gerenciamento de Pessoas do Microsservice de Pedidos")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Operation(summary = "Encontrar todas as Pessoas", description = "Encontrar todas as pessoas",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PessoaDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public Page<PessoaDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return pessoaService.obterTodos(paginacao);
    }

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Operation(summary = "Encontrar uma Pessoa", description = "Encontrar um pessoa",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<PessoaDTO> detalhar(@PathVariable @NotNull Long id) {
        PessoaDTO dto = pessoaService.obterPorId(id);

        return ResponseEntity.ok(dto);
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Adicionar uma nova Pessoa",
            description = "Adiciona uma nova Pessoa passando uma representação JSON de Pessoa!",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<PessoaDTO> cadastrar(@RequestBody @Valid PessoaDTO dto) {
        PessoaDTO pessoa = pessoaService.criarPessoa(dto);

        return ResponseEntity.ok(pessoa);
    }

    @PutMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @Operation(summary = "Atualizar uma Pessoa",
            description = "Atualiza uma Pessoa passando uma representação JSON ou XML de Pessoa!",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PessoaDTO dto) {
        PessoaDTO atualizado = pessoaService.atualizarPessoa(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma Pessoa",
            description = "Deleta uma Pessoa passando um Id que representa uma Pessoa!",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<PessoaDTO> remover(@PathVariable @NotNull Long id) {
        pessoaService.excluirPessoa(id);
        return ResponseEntity.noContent().build();
    }

}
