package com.barberflow.servicio_service.controller;

import com.barberflow.servicio_service.model.Servicio;
import com.barberflow.servicio_service.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> listarServicios() {
        return servicioService.obtenerServicios();
    }

    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@Valid @RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.guardarServicio(servicio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        return servicioService.obtenerServicioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(
            @PathVariable Long id,
            @Valid @RequestBody Servicio servicio) {

        return ResponseEntity.ok(
                servicioService.actualizarServicio(id, servicio)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {

        servicioService.eliminarServicio(id);

        return ResponseEntity.noContent().build();
    }
}