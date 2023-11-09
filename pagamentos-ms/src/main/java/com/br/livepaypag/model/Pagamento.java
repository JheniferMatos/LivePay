package com.br.livepaypag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Column
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    @Column
    private String nome;

    @Column
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;




}
