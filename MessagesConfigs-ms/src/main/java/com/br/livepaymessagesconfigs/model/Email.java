package com.br.livepaymessagesconfigs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidade que representa um e-mail no sistema.
 */
@Entity
@Table(name = "emails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String emailFrom;

    @Column
    private String email;

    @Column
    private String assunto;

    @Column
    private String texto;

    @Column
    private LocalDateTime enviarDataEmail;

    @Column
    private StatusEmail statusEmail;

}
