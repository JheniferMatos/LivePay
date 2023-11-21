package com.br.livepaypedidos.repository;

import com.br.livepaypedidos.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Pedidos.
 */
public interface PedidoRepository extends JpaRepository<Pedidos, Long> {
}
