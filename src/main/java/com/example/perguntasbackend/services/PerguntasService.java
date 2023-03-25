package com.example.perguntasbackend.services;


import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntasService {

    @Autowired
    private PerguntasRepository perguntasRepository;


    public Pergunta save (Pergunta pergunta) {
       return perguntasRepository.save(pergunta);
    }
    public Optional<Pergunta> findById(Long id) {
        return perguntasRepository.findById(id);
    }

    public Optional<Pergunta> findRespostaById(Long id) {
        return perguntasRepository.findRespostaById(id);
    }

    public List<Pergunta> findAll() {
        return perguntasRepository.findAll();
    }

    public Pergunta perguntaAleatoria() {
        return perguntasRepository.findPerguntaAleatoria();
    }
}
