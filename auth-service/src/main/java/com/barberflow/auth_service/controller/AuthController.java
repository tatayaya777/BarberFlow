package com.barberflow.auth_service.controller;

import com.barberflow.auth_service.model.Auth;
import com.barberflow.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Gestión de usuarios y autenticación")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Auth>> listarUsuarios() {
        return ResponseEntity.ok(authService.obtenerUsuarios());
    }

    @Operation(summary = "Buscar un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Auth> obtenerUsuario(
            @Parameter(description = "ID del usuario")
            @PathVariable Long id) {

        return authService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping
    public ResponseEntity<Auth> crearUsuario(@Valid @RequestBody Auth auth) {

        Auth nuevoUsuario = authService.guardarUsuario(auth);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Auth> actualizarUsuario(
            @Parameter(description = "ID del usuario")
            @PathVariable Long id,
            @Valid @RequestBody Auth auth) {

        return ResponseEntity.ok(authService.actualizarUsuario(id, auth));
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
            @Parameter(description = "ID del usuario")
            @PathVariable Long id) {

        authService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}