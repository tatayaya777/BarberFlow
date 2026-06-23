package com.barberflow.pago_service.repository;

import com.barberflow.pago_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}