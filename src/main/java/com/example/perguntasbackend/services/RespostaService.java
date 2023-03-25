package com.example.perguntasbackend.services;



import com.example.perguntasbackend.entities.Resposta;
import com.example.perguntasbackend.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    RespostaRepository respostaRepository;

    public Resposta save (Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public Optional<Resposta> findById(Long id) {
        return respostaRepository.findById(id);
    }
}
