package com.br.livepaypag.repository;

import com.br.livepaypag.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para operações relacionadas à entidade Pagamento.
 */
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}