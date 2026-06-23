package com.barberflow.auth_service.controller;

import com.barberflow.auth_service.model.Auth;
import com.barberflow.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<Auth> listarUsuarios() {
        return authService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auth> obtenerUsuario(@PathVariable Long id) {
        return authService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Auth crearUsuario(@RequestBody Auth auth) {
        return authService.guardarUsuario(auth);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auth> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Auth auth) {

        return ResponseEntity.ok(
                authService.actualizarUsuario(id, auth)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        authService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}