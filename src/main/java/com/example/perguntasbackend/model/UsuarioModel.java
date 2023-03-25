package com.example.perguntasbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
