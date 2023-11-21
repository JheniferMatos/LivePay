package com.br.livepaypedidos.repository;

import com.br.livepaypedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Produto.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
