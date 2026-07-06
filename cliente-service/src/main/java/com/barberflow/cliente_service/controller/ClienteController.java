package com.barberflow.cliente_service.controller;

import com.barberflow.cliente_service.model.Cliente;
import com.barberflow.cliente_service.service.ClienteService;
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
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @Operation(summary = "Buscar un cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(
            @Parameter(description = "ID del cliente")
            @PathVariable Long id) {

        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un cliente")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {

        Cliente nuevoCliente = clienteService.guardarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @Operation(summary = "Actualizar un cliente")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @Parameter(description = "ID del cliente")
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {

        return ResponseEntity.ok(
                clienteService.actualizarCliente(id, cliente)
        );
    }

    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @Parameter(description = "ID del cliente")
            @PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}