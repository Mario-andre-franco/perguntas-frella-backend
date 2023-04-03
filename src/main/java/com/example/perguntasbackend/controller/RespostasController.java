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

    @PostMapping("/salvar-respostas/usuario/{idUsuario}/pergunta/{idPergunta}")
    public ResponseEntity<Resposta> salvarRespostas(@PathVariable Long idUsuario, @PathVariable Long idPergunta, @RequestBody Resposta resposta) {
        Optional<Pergunta> procurarPergunta = perguntasService.findById(idPergunta);
        Optional<Usuario> usuarioExiste = usuarioService.findById(idUsuario);
        if (!usuarioExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        /*Aqui vamos pegar o id da resposta do banco e comparar
        com o que o usuário marcou em tela,
        Se forem iguais, chamamos o service para atualizar os pontos daquele usuário
        Caso esteja errado, jogamos um 404 apenas para questão de controle no frontend
        */

        Long idResposta = resposta.getOpcoes().get(0).getId();
        List<OpcaoResposta> listRespostaBanco = procurarPergunta.get().getRespostas().getOpcoes();
        Long idCorreto = null;
        for (int i = 0; i < listRespostaBanco.size(); i++) {
            if(listRespostaBanco.get(i).isVerdadeira()) {
                idCorreto = listRespostaBanco.get(i).getId();
            }
        }
        if(idResposta.equals(idCorreto)) {
            respostaService.atualizaPontos(usuarioExiste);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }


    @GetMapping("/buscar-repostas/{id}")
    @ResponseBody
    public Optional<Resposta> buscarRespostaPorId(@PathVariable Long id) {
        return respostaService.findById(id);
    }
}
