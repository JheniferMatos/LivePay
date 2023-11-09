package com.br.livepaypag.repository;

import com.br.livepaypag.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository  extends JpaRepository<Cartao, Long> {
}
