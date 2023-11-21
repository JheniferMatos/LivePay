package com.br.livepaypag.repository;

import com.br.livepaypag.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para operações relacionadas à entidade Cartao.
 */
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}