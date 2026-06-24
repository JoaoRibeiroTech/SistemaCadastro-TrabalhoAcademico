package com.sistema.service;

import com.sistema.model.EventoLog;
import com.sistema.repository.EventoLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoEvento {

    private final EventoLogRepository eventoLogRepository;

    public List<EventoLog> listar() {
        return eventoLogRepository.findAll();
    }
}