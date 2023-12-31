package com.br.livepaypedidos.controller;

import com.br.livepaypedidos.dto.PessoaDTO;
import com.br.livepaypedidos.dto.ProdutoDTO;
import com.br.livepaypedidos.service.PessoaService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(PessoaController.class)
@ContextConfiguration(classes = {PessoaController.class})
public class PessoaControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private PessoaService pessoaService;
    @Autowired
    private JacksonTester<PessoaDTO> pessoaDTOJson;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void testeListarTodos() throws Exception {
        mockMvc.perform(get("/pessoa"))
                .andExpect(status().isOk());
    }

    @Test
    void testeDetalharPorIdVerificandoOBody() throws Exception {
        PessoaDTO mockDto = new PessoaDTO(1L, "teste", "teste@gmail.com", "40400404004");
        mockDto.setId(1L);
        when(pessoaService.obterPorId(1L)).thenReturn(mockDto);
        mockMvc.perform(get("/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testeDoResponseEStatusDoCadastroDePagamento() throws Exception {
        PessoaDTO mockLerDto = new PessoaDTO(1L, "teste", "teste@gmail.com", "40400404004");

        when(pessoaService.criarPessoa(any())).thenReturn(mockLerDto);

        var response = mockMvc.perform(post("/pessoa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pessoaDTOJson.write(
                        new PessoaDTO(1L, "teste", "teste@gmail.com", "40400404004")
                ).getJson())).andReturn().getResponse();

        var jsonEsperado = pessoaDTOJson.write(
                new PessoaDTO(1L, "teste", "teste@gmail.com", "40400404004")).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
