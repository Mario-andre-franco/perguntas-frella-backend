package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntasRepository perguntasRepository;

    @GetMapping("/consultar-perguntas")
    @ResponseBody
    public List<Pergunta> listarPerguntas() {
        return perguntasRepository.findAll();
    }

    @PostMapping
    public Pergunta criarPergunta(@RequestBody Pergunta perguntaModel) {
        return perguntasRepository.save(perguntaModel);
    }
}
