package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.*;
import com.example.perguntasbackend.services.PerguntasService;
import com.example.perguntasbackend.services.PontosService;
import com.example.perguntasbackend.services.RespostaService;
import com.example.perguntasbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private PontosService pontosService;

    @PostMapping("/salvar-respostas/usuario/{idUsuario}/pergunta/{idPergunta}")
    public ResponseEntity<Resposta> salvarRespostas(@PathVariable Long idUsuario, @PathVariable Long idPergunta, @RequestBody Resposta resposta) {
        Optional<Pergunta> procurarPergunta = perguntasService.findById(idPergunta);
        Optional<Usuario> usuarioExiste = usuarioService.findById(idUsuario);
        Optional<Pontos> pontos = pontosService.findById(idUsuario);
        if (!procurarPergunta.isPresent() || !usuarioExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<OpcaoResposta> list = resposta.getOpcoes();
        List<OpcaoResposta> listRespostaBanco = procurarPergunta.get().getRespostas().getOpcoes();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listRespostaBanco.size(); j++) {
                if (list.get(i).getId().equals(listRespostaBanco.get(j).getId())) {
                    //salvou usuario com a pontuacao
                    respostaService.atualizaPontos(usuarioExiste);
                    pontosService.save(pontos.get(), usuarioExiste.get());
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/buscar-repostas/{id}")
    @ResponseBody
    public Optional<Resposta> buscarRespostaPorId(@PathVariable Long id) {
        return respostaService.findById(id);
    }
}
