package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.repositories.PerguntasRepository;
import com.example.perguntasbackend.services.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;

    @GetMapping("/consultar-perguntas")
    @ResponseBody
    public List<Pergunta> listarPerguntas() {
        return perguntasService.findAll();
    }

    @PostMapping
    public Pergunta criarPergunta(@RequestBody Pergunta perguntaModel) {
        return perguntasService.save(perguntaModel);
    }
}
