package com.example.perguntasbackend.services;


import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save (Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
