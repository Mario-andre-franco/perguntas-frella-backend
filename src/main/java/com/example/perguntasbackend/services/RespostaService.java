package com.example.perguntasbackend.services;



import com.example.perguntasbackend.entities.Resposta;
import com.example.perguntasbackend.entities.Usuario;
import com.example.perguntasbackend.repositories.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaService {
    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    UsuarioService usuarioService;

    public Resposta save (Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public Optional<Resposta> findById(Long id) {
        return respostaRepository.findById(id);
    }

    public void atualizaPontos(Optional<Usuario> usuarioExiste) {
        /*
        Aqui fazemos a atualização dos pontos do usuario.
        Se os pontos totais forem igual a null, setamos zero para evitar null pointer
        quando pegarmos o esse valor que vem do objeto usuarioExiste
         */
        Usuario atualizaPontosUsuario = usuarioExiste.get();
        if(atualizaPontosUsuario.getPontosTotais() == null) {
            atualizaPontosUsuario.setPontosTotais(0);
        }
        atualizaPontosUsuario.setPontosTotais(atualizaPontosUsuario.getPontosTotais() + 10);
        usuarioService.save(atualizaPontosUsuario);
    }

    public void atualizaPontosCategoria(Optional<Usuario> usuarioExiste) {
        /*
        Aqui fazemos a atualização dos pontos do usuario.
        Se os pontos totais forem igual a null, setamos zero para evitar null pointer
        quando pegarmos o esse valor que vem do objeto usuarioExiste
         */
        Usuario atualizaPontosUsuario = usuarioExiste.get();
        if(atualizaPontosUsuario.getPontosTotais() == null) {
            atualizaPontosUsuario.setPontosTotais(0);
        }
        atualizaPontosUsuario.setPontosTotais(atualizaPontosUsuario.getPontosTotais() + 30);
        usuarioService.save(atualizaPontosUsuario);
    }
}
