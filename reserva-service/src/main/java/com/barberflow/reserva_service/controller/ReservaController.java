package com.barberflow.reserva_service.controller;

import com.barberflow.reserva_service.model.Reserva;
import com.barberflow.reserva_service.service.ReservaService;
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
@RequestMapping("/reservas")
@Tag(name = "Reservas", description = "Gestión de reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Obtener todas las reservas")
    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaService.obtenerReservas());
    }

    @Operation(summary = "Buscar una reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(
            @Parameter(description = "ID de la reserva")
            @PathVariable Long id) {

        return reservaService.obtenerReservaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una reserva")
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva) {

        Reserva nuevaReserva = reservaService.guardarReserva(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @Operation(summary = "Actualizar una reserva")
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(
            @Parameter(description = "ID de la reserva")
            @PathVariable Long id,
            @Valid @RequestBody Reserva reserva) {

        return ResponseEntity.ok(
                reservaService.actualizarReserva(id, reserva)
        );
    }

    @Operation(summary = "Eliminar una reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(
            @Parameter(description = "ID de la reserva")
            @PathVariable Long id) {

        reservaService.eliminarReserva(id);

        return ResponseEntity.noContent().build();
    }
}