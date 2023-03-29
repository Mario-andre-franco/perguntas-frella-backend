package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Optional<Resposta> findById(Long id);
}
