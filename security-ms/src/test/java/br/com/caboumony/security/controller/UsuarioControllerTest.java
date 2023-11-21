package br.com.caboumony.security.controller;

import br.com.caboumony.security.usuario.Usuario;
import br.com.caboumony.security.usuario.UsuarioController;
import org.assertj.core.api.Assertions;
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
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WebMvcTest(UsuarioController.class)
@ContextConfiguration(classes = {UsuarioController.class})
public class UsuarioControllerTest {

//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @MockBean
//    private  produtoService;
//    @Autowired
//    private JacksonTester<ProdutoDTO> produtoDTOJson;
//
//    @Test
//    void testeDoResponseEStatusDoCadastroDePagamento() throws Exception {
//        ProdutoDTO mockLerDto = new ProdutoDTO(1L, "teste", new BigDecimal(100.0));
//
//        when(produtoService.criarProduto(any())).thenReturn(mockLerDto);
//
//        var response = mockMvc.perform(post("/produto")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(produtoDTOJson.write(
//                        new ProdutoDTO(1L, "teste", new BigDecimal(100.0))
//                ).getJson())).andReturn().getResponse();
//
//        var jsonEsperado = produtoDTOJson.write(
//                new ProdutoDTO(1L, "teste", new BigDecimal(100.0))).getJson();
//
//        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
//        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    }

}
