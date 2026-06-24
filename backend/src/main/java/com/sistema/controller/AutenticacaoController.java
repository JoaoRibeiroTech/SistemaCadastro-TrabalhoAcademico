package com.sistema.controller;

import com.sistema.dto.*;
import com.sistema.service.ServicoAutenticacao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    private final ServicoAutenticacao servicoAutenticacao;

    @PostMapping("/registrar")
    public RespostaAutenticacaoDTO registrar(@RequestBody RegistroDTO dto) {
        return servicoAutenticacao.registrar(dto);
    }

    @PostMapping("/entrar")
    public RespostaAutenticacaoDTO entrar(@RequestBody LoginDTO dto) {
        return servicoAutenticacao.entrar(dto);
    }
}