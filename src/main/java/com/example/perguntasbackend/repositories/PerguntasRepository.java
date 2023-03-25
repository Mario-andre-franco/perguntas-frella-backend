package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerguntasRepository extends JpaRepository<Pergunta, Long>{

    Optional<Pergunta> findById(Long id);
    Optional<Pergunta> findRespostaById(Long id);
}
