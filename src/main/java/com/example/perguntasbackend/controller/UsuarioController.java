package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Pontos;
import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.services.PontosService;
import com.example.perguntasbackend.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PontosService pontosService;


    @PostMapping("/login")
    public ResponseEntity<Usuario> loginFake (@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEmail = usuarioService.findByEmail(usuario.getEmail());
        if(!usuarioEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            if(!usuarioEmail.get().getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usuario);
            }
        }
        if (usuarioEmail.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok().body(usuarioEmail.get());
        }
        else {
            return ResponseEntity.badRequest().body(usuarioEmail.get());
        }
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExiste = usuarioService.findByEmail(usuario.getEmail());
        if (usuarioExiste.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuario);
        }
        Usuario usuarioSalvo = usuarioService.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping("/consultar-pontos/{id}")
    public Optional<Usuario> consultarPontos(@PathVariable Long id) {
        Optional<Usuario> usuarioExiste = usuarioService.findById(id);
        if (!usuarioExiste.isPresent()) {
            return usuarioService.findById(id);
        }
        return usuarioService.findPontosById(id);

    }

    @GetMapping("/consulta-por-email/")
    @ResponseBody
    public Optional<Usuario> consultarUsuarioPorEmail(@RequestBody Usuario usuario) {
        return usuarioService.findByEmail(usuario.getEmail());
    }

    @GetMapping("/consultar-pontos/")
    @ResponseBody
    public List<Pontos> consultarPontosTotais() {
        return pontosService.findAll();
    }

    @PostMapping("/{id}/salvar-pontos")
    public ResponseEntity<?> salvarPontos(@PathVariable Long id, @RequestBody int pontos) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        Optional<Usuario> usuarioOptional = usuarioService.findPontosById(id);
        Pontos pontos1 = new Pontos();
        pontos1.setPontos(pontos);
        pontos1.setUsuario(usuario.get());
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pontosService.save(pontos1,usuarioOptional.get());

        return ResponseEntity.ok(pontos);
    }

}
