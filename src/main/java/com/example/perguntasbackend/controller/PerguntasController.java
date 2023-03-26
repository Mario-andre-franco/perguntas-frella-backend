package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.services.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntasService perguntasService;

    @GetMapping("/consultar-perguntas")
    @ResponseBody
    public List<Pergunta> listarPerguntas() {
        return perguntasService.findAll();
    }

    @PostMapping("/criar-pergunta")
    public Pergunta criarPergunta(@RequestBody Pergunta perguntaModel) {
        return perguntasService.save(perguntaModel);
    }

    @GetMapping("/gerar-pergunta")
    public ResponseEntity<Pergunta> gerarPergunta() {

        Pergunta getPerguntas = perguntasService.perguntaAleatoria();
        if(getPerguntas != null) {
            return ResponseEntity.ok().body(getPerguntas);
        }
        return ResponseEntity.badRequest().build();
    }
}
