package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Pergunta;
import com.example.perguntasbackend.entities.Resposta;
import com.example.perguntasbackend.entities.Usuario;
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

    @PostMapping("/salvar-respostas/{idPergunta}/{idUsuario}")
    public ResponseEntity<Resposta> salvarRespostas(@PathVariable Long idPergunta,@PathVariable Long idUsuario, @RequestBody Resposta resposta) {
        Optional<Pergunta> procurarPergunta = perguntasService.findById(idPergunta);
        Optional<Usuario> usuario = usuarioService.findById(idUsuario);
        if(!procurarPergunta.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Resposta respostaSalva = respostaService.save(resposta);

        return ResponseEntity.ok().body(respostaSalva);

    }
}
