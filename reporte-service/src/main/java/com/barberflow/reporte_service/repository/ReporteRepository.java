package com.barberflow.reporte_service.repository;

import com.barberflow.reporte_service.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}