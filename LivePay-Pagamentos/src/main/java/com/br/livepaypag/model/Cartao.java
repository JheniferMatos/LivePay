package com.br.livepaypag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cartoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column
    private String nome;

    @NotBlank
    @Size(max = 16)
    @Column
    private String numero;

    @NotBlank
    @Size(max=7)
    @Column
    private String dataExpiracao;

    @NotBlank
    @Size(min=3, max=3)
    @Column
    private String codigoSeguranca;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private TipoDePagamento tipoDePagamento;

}
