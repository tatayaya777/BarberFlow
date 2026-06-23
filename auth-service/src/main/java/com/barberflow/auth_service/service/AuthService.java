package com.barberflow.auth_service.service;

import com.barberflow.auth_service.model.Auth;
import com.barberflow.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public List<Auth> obtenerUsuarios() {
        return authRepository.findAll();
    }

    public Auth guardarUsuario(Auth auth) {
        return authRepository.save(auth);
    }

    public Optional<Auth> obtenerUsuarioPorId(Long id) {
        return authRepository.findById(id);
    }

    public Auth actualizarUsuario(Long id, Auth authActualizado) {

        Auth auth = authRepository.findById(id)
                .orElseThrow();

        auth.setUsername(authActualizado.getUsername());
        auth.setPassword(authActualizado.getPassword());
        auth.setRol(authActualizado.getRol());

        return authRepository.save(auth);
    }

    public void eliminarUsuario(Long id) {
        authRepository.deleteById(id);
    }
}