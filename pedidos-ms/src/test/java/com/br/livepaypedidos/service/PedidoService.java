//package com.br.livepaypedidos.service;
//
//import com.br.livepaypedidos.dto.LerPedidoDTO;
//import com.br.livepaypedidos.model.Pedidos;
//import com.br.livepaypedidos.repository.PedidoRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//import static javax.management.Query.eq;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
//
//public class PedidoService {
//
//    @Mock
//    private PedidoRepository pedidoRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private PedidoService pedidoService;
//
//    @Test
//    public void testObterTodos() {
//        // Criar instâncias de objetos necessários para o teste
//        Pageable pageable = Pageable.unpaged();
//        Pedidos pedido1 = new Pedidos(/* Preencher com dados necessários */);
//        Pedidos pedido2 = new Pedidos(/* Preencher com dados necessários */);
//
//        // Configurar comportamento simulado para os métodos do mock
//        when(pedidoRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(pedido1, pedido2)));
//        when(modelMapper.map(any(), eq(LerPedidoDTO.class)))
//                .thenAnswer(invocation -> modelMapper.map(invocation.getArgument(0), LerPedidoDTO.class));
//
//        // Chamar o método que será testado
//        Page<LerPedidoDTO> resultado = pedidoService.testObterTodos(pageable);
//
//        // Verificar se os métodos dos mocks foram chamados corretamente
//        verify(pedidoRepository, times(1)).findAll(pageable);
//        verify(modelMapper, times(2)).map(any(), eq(LerPedidoDTO.class));
//
//        // Verificar se o resultado é o esperado
//        // Aqui você pode adicionar as asserções necessárias conforme seus requisitos
//        assertNotNull(resultado);
//        assertEquals(2, resultado.getTotalElements());
//        // Adicione mais asserções conforme necessário
//    }
//
//}
