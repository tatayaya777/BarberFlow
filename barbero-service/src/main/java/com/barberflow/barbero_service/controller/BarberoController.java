package com.barberflow.barbero_service.controller;

import com.barberflow.barbero_service.model.Barbero;
import com.barberflow.barbero_service.service.BarberoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barberos")
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public List<Barbero> listarBarberos() {
        return barberoService.obtenerBarberos();
    }

    @PostMapping
    public ResponseEntity<Barbero> crearBarbero(@Valid @RequestBody Barbero barbero) {
        return ResponseEntity.ok(barberoService.guardarBarbero(barbero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbero> obtenerBarbero(@PathVariable Long id) {
        return barberoService.obtenerBarberoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbero> actualizarBarbero(
            @PathVariable Long id,
            @Valid @RequestBody Barbero barbero) {

        return ResponseEntity.ok(barberoService.actualizarBarbero(id, barbero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBarbero(@PathVariable Long id) {
        barberoService.eliminarBarbero(id);
        return ResponseEntity.noContent().build();
    }
}