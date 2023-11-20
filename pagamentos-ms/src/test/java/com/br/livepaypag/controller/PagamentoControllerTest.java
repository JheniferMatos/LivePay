package com.br.livepaypag.controller;

import com.br.livepaypag.dto.CartaoDto;
import com.br.livepaypag.dto.LerPagamentoDTO;
import com.br.livepaypag.dto.PagamentoDTO;
import com.br.livepaypag.model.Cartao;
import com.br.livepaypag.model.InformacaoPedido;
import com.br.livepaypag.model.Status;
import com.br.livepaypag.model.TipoDePagamento;
import com.br.livepaypag.service.CartaoService;
import com.br.livepaypag.service.PagamentoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(PagamentoController.class)
@ContextConfiguration(classes = {PagamentoController.class})
class PagamentoControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private CartaoService cartaoService;
    @MockBean
    private PagamentoService pagamentoService;
    @Autowired
    private JacksonTester<PagamentoDTO> pagamentoDTOJson;
    @Autowired
    private JacksonTester<CartaoDto> cartaoDTOJson;
    @Autowired
    private JacksonTester<LerPagamentoDTO> lerPagamentoDTOJson;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void testeListarTodos() throws Exception {
        mockMvc.perform(get("/pagamentos"))
                .andExpect(status().isOk());
    }
    @Test
    void testeDetalharPorIdVerificandoOBody() throws Exception {
        LerPagamentoDTO mockDto = new LerPagamentoDTO();
        mockDto.setId(1L);
        when(pagamentoService.obterPorId(1L)).thenReturn(mockDto);
        mockMvc.perform(get("/pagamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void testeDoResponseEStatusDoCadastroDePagamento() throws Exception {
        LerPagamentoDTO mockLerDto = new LerPagamentoDTO(1l,new InformacaoPedido(),"teste","teste@teste.com",
                new Cartao(), Status.CRIADO);

        when(pagamentoService.criarPagamento(any())).thenReturn(mockLerDto);

        var response = mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pagamentoDTOJson.write(
                                new PagamentoDTO(1l, 200.0, 1L, "teste","teste@teste.com",
                                        132131l, Status.CRIADO)
                        ).getJson())).andReturn().getResponse();

        var jsonEsperado = lerPagamentoDTOJson.write(
                new LerPagamentoDTO(1l, new InformacaoPedido(), "teste","teste@teste.com",
                        new Cartao(), Status.CRIADO)).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void testeDoResponseEStatusDaConfirmacaoDoPagamento() throws Exception {
        PagamentoDTO pagamentoDTO = new PagamentoDTO(1l,200.0, 1L, "teste","teste@teste.com",
                132131l, Status.CRIADO);

        // Isso é uma "pré-config", ou seja, quando o método testado for chamado ele fará exatamente o que está abaixo
        doAnswer(invocation -> {
            pagamentoDTO.setStatus(Status.CONFIRMADO);
            return null;
        }).when(pagamentoService).confirmarPagamento(anyLong());

        pagamentoService.confirmarPagamento(1L);
        var response = mockMvc.perform(post("/pagamentos/1")).andReturn().getResponse();

        assertEquals(Status.CONFIRMADO, pagamentoDTO.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
    @Test
    void testeDoResponseEStatusdaCriacaoDeCartao() throws Exception {
        CartaoDto cartaoDto = new CartaoDto(1l,"teste","123456","12/23",
                "358", TipoDePagamento.CREDITO);

        when(cartaoService.criarPagamento(any())).thenReturn(cartaoDto);

        var response = mockMvc.perform(post("/pagamentos/cartao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cartaoDTOJson.write(
                        new CartaoDto(1l,"teste","123456","12/23",
                                "358", TipoDePagamento.CREDITO)
                ).getJson())).andReturn().getResponse();

        var jsonEsperado = cartaoDTOJson.write(
                new CartaoDto(1l,"teste","123456","12/23",
                        "358", TipoDePagamento.CREDITO)).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    void testeDoResponseEStatusDoExcluirPagamento() throws Exception {
        PagamentoDTO pagamentoDTO = new PagamentoDTO(1l, 200.0, 1L, "teste","teste@teste.com",
                132131l, Status.CRIADO);

        doAnswer(invocation -> {
            pagamentoDTO.setStatus(Status.CANCELADO);
            return null;
        }).when(pagamentoService).confirmarPagamento(anyLong());

        mockMvc.perform(delete("/pagamentos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        pagamentoService.excluirPagamento(1L);
        var response = mockMvc.perform(post("/pagamentos/1")).andReturn().getResponse();

        assertEquals(Status.CANCELADO, pagamentoDTO.getStatus());
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}