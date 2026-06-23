package com.barberflow.reporte_service.controller;

import com.barberflow.reporte_service.model.Reporte;
import com.barberflow.reporte_service.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.obtenerReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable Long id) {
        return reporteService.obtenerReportePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {
        return reporteService.guardarReporte(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizarReporte(
            @PathVariable Long id,
            @RequestBody Reporte reporte) {

        return ResponseEntity.ok(
                reporteService.actualizarReporte(id, reporte)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}