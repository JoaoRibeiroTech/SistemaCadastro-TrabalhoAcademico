package com.sistema.controller;

import com.sistema.model.EventoLog;
import com.sistema.service.ServicoEvento;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MonitoramentoController {

    private final ServicoEvento servicoEvento;

    @GetMapping("/eventos")
    public List<EventoLog> listar() {
        return servicoEvento.listar();
    }
}