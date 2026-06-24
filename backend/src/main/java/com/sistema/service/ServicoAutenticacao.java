package com.sistema.service;

import com.sistema.dto.*;
import com.sistema.model.Perfil;
import com.sistema.model.Usuario;
import com.sistema.repository.UsuarioRepository;
import com.sistema.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicoAutenticacao {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RespostaAutenticacaoDTO registrar(RegistroDTO dto) {
        Usuario usuario = Usuario.builder()
                .nomeUsuario(dto.getNomeUsuario())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(Perfil.ROLE_USUARIO)
                .build();

        usuarioRepository.save(usuario);
        return new RespostaAutenticacaoDTO(jwtService.gerarToken(usuario));
    }

    public RespostaAutenticacaoDTO entrar(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByNomeUsuario(dto.getNomeUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return new RespostaAutenticacaoDTO(jwtService.gerarToken(usuario));
    }
}