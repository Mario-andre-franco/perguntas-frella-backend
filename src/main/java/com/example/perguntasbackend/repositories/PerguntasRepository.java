package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntasRepository extends JpaRepository<Pergunta, Long>{
}
