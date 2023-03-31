package com.example.perguntasbackend.services;


import com.example.perguntasbackend.entities.Pontos;
import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.repositories.PontosRepository;
import com.example.perguntasbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontosService {

    @Autowired
    PontosRepository pontosRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public Pontos save (Pontos pontos, Usuario usuario) {
        return pontosRepository.save(pontos);
    }
    public List<Pontos> findAll() {
        return pontosRepository.findAll();
    }

    public Optional<Pontos> findById(Long idUsuario) {
        return pontosRepository.findById(idUsuario);
    }
}
