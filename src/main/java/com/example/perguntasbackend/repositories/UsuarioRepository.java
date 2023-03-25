package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
