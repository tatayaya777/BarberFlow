package com.barberflow.cliente_service.service;

import com.barberflow.cliente_service.exception.ResourceNotFoundException;
import com.barberflow.cliente_service.model.Cliente;
import com.barberflow.cliente_service.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes() {

        logger.info("Obteniendo lista de clientes.");

        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {

        logger.info("Creando cliente: {}", cliente.getNombre());

        Cliente nuevoCliente = clienteRepository.save(cliente);

        logger.info("Cliente creado correctamente con ID: {}", nuevoCliente.getId());

        return nuevoCliente;
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {

        logger.info("Buscando cliente con ID: {}", id);

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            logger.warn("No se encontró el cliente con ID: {}", id);
        }

        return cliente;
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {

        logger.info("Actualizando cliente con ID: {}", id);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setEmail(clienteActualizado.getEmail());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        logger.info("Cliente actualizado correctamente.");

        return clienteGuardado;
    }

    public void eliminarCliente(Long id) {

        logger.info("Eliminando cliente con ID: {}", id);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        clienteRepository.delete(cliente);

        logger.info("Cliente eliminado correctamente.");
    }
}