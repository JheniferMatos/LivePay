package com.br.livepaypedidos.controller;

import com.br.livepaypedidos.dto.ProdutoDTO;
import com.br.livepaypedidos.service.ProdutoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(ProdutoController.class)
@ContextConfiguration(classes = {ProdutoController.class})
public class ProdutoControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ProdutoService produtoService;
    @Autowired
    private JacksonTester<ProdutoDTO> produtoDTOJson;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void testeListarTodos() throws Exception {
        mockMvc.perform(get("/produto"))
                .andExpect(status().isOk());
    }

    @Test
    void testeDetalharPorIdVerificandoOBody() throws Exception {
        ProdutoDTO mockDto = new ProdutoDTO(1L, "teste", new BigDecimal(100.0));
        mockDto.setId(1L);
        when(produtoService.obterPorId(1L)).thenReturn(mockDto);
        mockMvc.perform(get("/produto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testeDoResponseEStatusDoCadastroDePagamento() throws Exception {
        ProdutoDTO mockLerDto = new ProdutoDTO(1L, "teste", new BigDecimal(100.0));

        when(produtoService.criarProduto(any())).thenReturn(mockLerDto);

        var response = mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoDTOJson.write(
                        new ProdutoDTO(1L, "teste", new BigDecimal(100.0))
                ).getJson())).andReturn().getResponse();

        var jsonEsperado = produtoDTOJson.write(
                new ProdutoDTO(1L, "teste", new BigDecimal(100.0))).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }





}
