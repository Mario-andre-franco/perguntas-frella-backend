package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.OpcaoResposta;
import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.services.PerguntasService;
import com.example.perguntasbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/consultar-perguntas/{id}")
    @ResponseBody
    public Optional<Pergunta> consultarPorId(@PathVariable Long id) {
        return perguntasService.findById(id);
    }
    @PostMapping("/criar-pergunta")
    public Pergunta criarPergunta(@RequestBody Pergunta perguntaModel) {

        return perguntasService.save(perguntaModel);
    }

    @GetMapping("/gerar-pergunta")
    public ResponseEntity<Pergunta> gerarPergunta() {

        /*
        Método responsavel por pegar uma pergunta aleatoria,
        no banco de dados seguindo a query do perguntasService:
        select * from tb_perguntas order by rand() limit 1
         */
        Pergunta getPerguntas = perguntasService.perguntaAleatoria();
        if(getPerguntas != null) {
            return ResponseEntity.ok().body(getPerguntas);
        }
        return ResponseEntity.badRequest().build();
    }

}
