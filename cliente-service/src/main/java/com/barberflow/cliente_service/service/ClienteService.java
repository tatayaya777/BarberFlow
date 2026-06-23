package com.barberflow.cliente_service.service;

import com.barberflow.cliente_service.model.Cliente;
import com.barberflow.cliente_service.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger =
            LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes() {

        logger.info("Listando clientes");

        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {

        logger.info("Guardando cliente: {}", cliente.getNombre());

        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {

        logger.info("Buscando cliente con ID: {}", id);

        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {

        logger.info("Actualizando cliente con ID: {}", id);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow();

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setEmail(clienteActualizado.getEmail());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {

        logger.info( "Eliminando cliente con ID: {}", id);

        clienteRepository.deleteById(id);
    }
}