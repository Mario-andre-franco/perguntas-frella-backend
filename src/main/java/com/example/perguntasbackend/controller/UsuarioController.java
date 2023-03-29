package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;


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

    @PutMapping("/alterar-email/{id}/email")
    public ResponseEntity<Usuario> alterarEmail(@PathVariable Long id , @RequestParam String novoEmail) {
        Optional<Usuario> usuarioExiste = usuarioService.findById(id);
        if(!usuarioExiste.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioExiste.get();
        usuario.setEmail(novoEmail);
        Usuario usuarioAtualizado = usuarioService.save(usuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

}
