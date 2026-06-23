package com.barberflow.servicio_service.repository;

import com.barberflow.servicio_service.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}