package com.sistema.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoEvento;
    private String entidade;
    private Long entidadeId;

    @Column(length = 2000)
    private String detalhes;

    private LocalDateTime dataEvento;
}