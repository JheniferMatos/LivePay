package com.br.livepaypedidos.controller;

import com.br.livepaypedidos.dto.CriarPedidoDTO;
import com.br.livepaypedidos.dto.LerPedidoDTO;
import com.br.livepaypedidos.dto.PessoaDTO;
import com.br.livepaypedidos.model.Pessoa;
import com.br.livepaypedidos.model.Produto;
import com.br.livepaypedidos.service.PedidoService;
import com.br.livepaypedidos.service.PessoaService;
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
@WebMvcTest(PedidoController.class)
@ContextConfiguration(classes = {PedidoController.class})
public class PedidoControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private PedidoService pedidoService;
    @Autowired
    private JacksonTester<CriarPedidoDTO> criarPedidoDTOJson;
    @Autowired
    private JacksonTester<LerPedidoDTO> lerPedidoDTOJson;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void testeListarTodos() throws Exception {
        mockMvc.perform(get("/pedido"))
                .andExpect(status().isOk());
    }

    @Test
    void testeDetalharPorIdVerificandoOBody() throws Exception {
        LerPedidoDTO mockDto = new LerPedidoDTO();
        mockDto.setId(1L);
        when(pedidoService.obterPorId(1L)).thenReturn(mockDto);
        mockMvc.perform(get("/pedido/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testeDoResponseEStatusDoCadastroDePagamento() throws Exception {
        LerPedidoDTO mockLerDto = new LerPedidoDTO();

        when(pedidoService.criarPedido(any())).thenReturn(mockLerDto);

        var response = mockMvc.perform(post("/pedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(criarPedidoDTOJson.write(
                        new CriarPedidoDTO()
                ).getJson())).andReturn().getResponse();

        var jsonEsperado = lerPedidoDTOJson.write(
                new LerPedidoDTO()).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
