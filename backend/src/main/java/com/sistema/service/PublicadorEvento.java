package com.sistema.service;

import com.sistema.model.EventoLog;
import com.sistema.repository.EventoLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PublicadorEvento {

    private final RabbitTemplate rabbitTemplate;
    private final EventoLogRepository eventoLogRepository;

    @Value("${app.rabbit.queue}")
    private String fila;

    public void publicar(String tipo, String entidade, Long id, String detalhes) {
        EventoLog evento = EventoLog.builder()
                .tipoEvento(tipo)
                .entidade(entidade)
                .entidadeId(id)
                .detalhes(detalhes)
                .dataEvento(LocalDateTime.now())
                .build();

        eventoLogRepository.save(evento);
        rabbitTemplate.convertAndSend(fila, detalhes);
    }
}