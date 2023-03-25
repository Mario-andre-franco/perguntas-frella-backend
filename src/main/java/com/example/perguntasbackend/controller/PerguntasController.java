package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.model.PerguntaModel;
import com.example.perguntasbackend.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntasRepository perguntasRepository;

    @GetMapping("/consultar-perguntas")
    @ResponseBody
    public List<PerguntaModel> listarPerguntas() {
        return perguntasRepository.findAll();
    }

    @PostMapping
    public PerguntaModel criarPergunta(@RequestBody PerguntaModel perguntaModel) {
        return perguntasRepository.save(perguntaModel);
    }
}
