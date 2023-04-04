package com.example.perguntasbackend.controller;


import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.services.UsuarioService;
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


    @PostMapping("/login")
    public ResponseEntity<Usuario> loginFake(@RequestBody Usuario usuario) {
        /*
        Método responsável por fazer um 'login'.
        Não é considerado boa pratica fazer essa comparação..
        Mas aqui no caso verificamos se o email do usuario existe no banco,
        se existir comparamos as senhas da tela, com a senha cadastrada no banco,
        se forem igual retornamos ok

         */
        Optional<Usuario> usuarioEmail = usuarioService.findByEmail(usuario.getEmail());
        if (!usuarioEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            if (!usuarioEmail.get().getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usuario);
            }
        }
        if (usuarioEmail.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok().body(usuarioEmail.get());
        } else {
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

    @GetMapping("/consulta-por-email/")
    @ResponseBody
    public Optional<Usuario> consultarUsuarioPorEmail(@RequestBody Usuario usuario) {
        return usuarioService.findByEmail(usuario.getEmail());
    }

    @GetMapping("/consultar-pontos/")
    @ResponseBody
    public List<Usuario> consultarPontosTotais() {
        return usuarioService.findPontosByNome();
    }

}
