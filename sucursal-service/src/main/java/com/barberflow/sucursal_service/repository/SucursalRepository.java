package com.barberflow.sucursal_service.repository;

import com.barberflow.sucursal_service.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}