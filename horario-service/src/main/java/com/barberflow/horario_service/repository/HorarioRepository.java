package com.barberflow.horario_service.repository;

import com.barberflow.horario_service.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
}