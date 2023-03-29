package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.OpcaoResposta;
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
        if(!procurarPergunta.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<OpcaoResposta> respostaList = resposta.getOpcoes();

        //fazer um for onde, o id da opcao marcada em tela é igual ao id que veio do procurar pergunta
        for (OpcaoResposta opcao: respostaList)
        {
            Long marcada = opcao.getId();
            if (procurarPergunta.get().getId().equals(marcada)) {
                return null;
            }
        }

        return null;

    }

    @GetMapping("/buscar-repostas/{id}")
    @ResponseBody
    public Optional<Resposta> buscarRespostaPorId(@PathVariable Long id) {
        return respostaService.findById(id);
    }
}
