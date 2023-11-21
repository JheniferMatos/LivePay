package com.br.livepaypedidos.repository;

import com.br.livepaypedidos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Pessoa.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
