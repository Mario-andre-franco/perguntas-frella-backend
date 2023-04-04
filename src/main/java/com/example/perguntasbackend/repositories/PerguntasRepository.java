package com.example.perguntasbackend.repositories;

import com.example.perguntasbackend.entities.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntasRepository extends JpaRepository<Pergunta, Long>{

    Optional<Pergunta> findById(Long id);
    @Query(value = "select * from tb_perguntas order by rand() limit 1", nativeQuery = true)
    Pergunta findPerguntaAleatoria();

    @Query(value = "select * from tb_perguntas where categoria = :Idcategoria order by rand() limit 1", nativeQuery = true)
    Pergunta findPerguntaByCategoria(String Idcategoria);
}
