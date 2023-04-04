package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Pontos;
import com.example.perguntasbackend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);

    @Query(value = "select * from tb_usuarios where pontos is not null order by pontos desc ", nativeQuery = true)
    List<Usuario> findPontosByNome();

}
