package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);



    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<Usuario> loginFake (@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEmail = usuarioService.findByEmail(usuario.getEmail());
        if(!usuarioEmail.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        else {
            if (usuarioEmail.get().getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.OK).body(usuarioEmail.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExiste = usuarioService.findByEmail(usuario.getEmail());
        if (usuarioExiste.isPresent()) {
            return ResponseEntity.badRequest().build();
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

    private String encryptPassword(String senha) {
        return DigestUtils.sha256Hex(senha);
    }

}
