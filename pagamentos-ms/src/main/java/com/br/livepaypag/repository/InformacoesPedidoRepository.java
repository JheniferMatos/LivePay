package com.br.livepaypag.repository;

import com.br.livepaypag.model.InformacaoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para operações relacionadas à entidade InformacaoPedido.
 */
public interface InformacoesPedidoRepository extends JpaRepository<InformacaoPedido, Long> {
}