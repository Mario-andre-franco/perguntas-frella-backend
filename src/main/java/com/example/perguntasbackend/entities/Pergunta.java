package com.example.perguntasbackend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_perguntas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo_pergunta")
    private String tituloPergunta;

    @Column
    private String categoria;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resposta_id")
    private Resposta respostas;

}
