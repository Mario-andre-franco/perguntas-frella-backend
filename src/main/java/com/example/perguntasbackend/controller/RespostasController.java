package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Resposta;
import com.example.perguntasbackend.services.PerguntasService;
import com.example.perguntasbackend.services.RespostaService;
import com.example.perguntasbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/respostas")
public class RespostasController {

    @Autowired
    private PerguntasService perguntasService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RespostaService respostaService;

    @PostMapping("/salvar-respostas")
    public ResponseEntity<Resposta> salvarRespostas(@RequestBody Resposta resposta) {
        Optional<Resposta>optionalResposta = respostaService.findById(resposta.getId());

        if (!optionalResposta.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Resposta respostaInsert = respostaService.save(resposta);

        return ResponseEntity.status(HttpStatus.CREATED).body(respostaInsert);
    }
}
