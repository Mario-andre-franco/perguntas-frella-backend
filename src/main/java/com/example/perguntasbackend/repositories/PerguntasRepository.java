package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.model.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntasRepository extends JpaRepository<PerguntaModel, Long>{
}
