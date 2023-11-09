package com.br.livepaymessagesconfigs.repository;

import com.br.livepaymessagesconfigs.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {



}
