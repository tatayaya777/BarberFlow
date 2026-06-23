package com.barberflow.sucursal_service.controller;

import com.barberflow.sucursal_service.model.Sucursal;
import com.barberflow.sucursal_service.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listarSucursales() {
        return sucursalService.obtenerSucursales();
    }

    @PostMapping
    public Sucursal crearSucursal(@Valid @RequestBody Sucursal sucursal) {
        return sucursalService.guardarSucursal(sucursal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(@PathVariable Long id) {
        return sucursalService.obtenerSucursalPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(
            @PathVariable Long id,
            @Valid @RequestBody Sucursal sucursal) {

        return ResponseEntity.ok(
                sucursalService.actualizarSucursal(id, sucursal)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {

        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}