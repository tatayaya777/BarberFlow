package com.barberflow.horario_service.controller;

import com.barberflow.horario_service.model.Horario;
import com.barberflow.horario_service.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public List<Horario> listarHorarios() {
        return horarioService.obtenerHorarios();
    }

    @PostMapping
    public ResponseEntity<Horario> crearHorario(@Valid @RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.guardarHorario(horario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerHorario(@PathVariable Long id) {
        return horarioService.obtenerHorarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizarHorario(
            @PathVariable Long id,
            @Valid @RequestBody Horario horario) {

        return ResponseEntity.ok(
                horarioService.actualizarHorario(id, horario)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {

        horarioService.eliminarHorario(id);

        return ResponseEntity.noContent().build();
    }
}