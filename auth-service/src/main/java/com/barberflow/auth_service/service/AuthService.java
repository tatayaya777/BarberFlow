package com.barberflow.auth_service.service;

import com.barberflow.auth_service.exception.ResourceNotFoundException;
import com.barberflow.auth_service.model.Auth;
import com.barberflow.auth_service.repository.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthRepository authRepository;

    public List<Auth> obtenerUsuarios() {
        logger.info("Obteniendo lista de usuarios.");
        return authRepository.findAll();
    }

    public Optional<Auth> obtenerUsuarioPorId(Long id) {
        logger.info("Buscando usuario con ID: {}", id);

        Optional<Auth> auth = authRepository.findById(id);

        if (auth.isEmpty()) {
            logger.warn("No se encontró el usuario con ID: {}", id);
        }

        return auth;
    }

    public Auth guardarUsuario(Auth auth) {
        logger.info("Creando usuario: {}", auth.getUsername());

        Auth nuevoUsuario = authRepository.save(auth);

        logger.info("Usuario creado correctamente con ID: {}", nuevoUsuario.getId());

        return nuevoUsuario;
    }

    public Auth actualizarUsuario(Long id, Auth authActualizado) {

        logger.info("Actualizando usuario con ID: {}", id);

        Auth auth = authRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        auth.setUsername(authActualizado.getUsername());
        auth.setPassword(authActualizado.getPassword());
        auth.setRol(authActualizado.getRol());

        logger.info("Usuario actualizado correctamente.");

        return authRepository.save(auth);
    }

    public void eliminarUsuario(Long id) {

        logger.info("Eliminando usuario con ID: {}", id);

        Auth auth = authRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        authRepository.delete(auth);

        logger.info("Usuario eliminado correctamente.");
    }

}