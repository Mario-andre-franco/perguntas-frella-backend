package com.example.perguntasbackend.repositories;


import com.example.perguntasbackend.entities.Pontos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PontosRepository extends JpaRepository <Pontos, Long>  {

}
