package com.barberflow.auth_service.repository;

import com.barberflow.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}