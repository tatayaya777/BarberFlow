package com.barberflow.notificacion_service.controller;

import com.barberflow.notificacion_service.model.Notificacion;
import com.barberflow.notificacion_service.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return notificacionService.obtenerNotificaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacion(@PathVariable Long id) {
        return notificacionService.obtenerNotificacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Notificacion crearNotificacion(@Valid @RequestBody Notificacion notificacion) {
        return notificacionService.guardarNotificacion(notificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizarNotificacion(
            @PathVariable Long id,
            @Valid @RequestBody Notificacion notificacion) {

        return ResponseEntity.ok(
                notificacionService.actualizarNotificacion(id, notificacion)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}