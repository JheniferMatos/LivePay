package com.br.livepaypag.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "InformacoesPedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformacaoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double valor;

}
