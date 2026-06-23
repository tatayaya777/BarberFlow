package com.barberflow.notificacion_service.repository;

import com.barberflow.notificacion_service.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}