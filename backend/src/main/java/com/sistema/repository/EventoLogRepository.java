package com.sistema.repository;

import com.sistema.model.EventoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoLogRepository extends JpaRepository<EventoLog, Long> {
}