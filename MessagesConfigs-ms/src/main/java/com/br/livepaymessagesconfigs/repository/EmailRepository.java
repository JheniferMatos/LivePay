package com.br.livepaymessagesconfigs.repository;

import com.br.livepaymessagesconfigs.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para a entidade {@link Email}.
 */
public interface EmailRepository extends JpaRepository<Email, Long> {
}
