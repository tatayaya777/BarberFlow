package com.barberflow.barbero_service.repository;

import com.barberflow.barbero_service.model.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberoRepository extends JpaRepository<Barbero, Long> {
}