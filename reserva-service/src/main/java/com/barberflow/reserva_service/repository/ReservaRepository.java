package com.barberflow.reserva_service.repository;

import com.barberflow.reserva_service.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}