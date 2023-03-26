package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.repositories.UsuarioRepository;
import com.example.perguntasbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<?> loginFake (@RequestBody Usuario usuario) {
        if(usuarioService.findById(usuario.getId()).isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
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

}
