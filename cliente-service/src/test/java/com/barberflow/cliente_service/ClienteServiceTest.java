package com.barberflow.cliente_service;

import com.barberflow.cliente_service.exception.ResourceNotFoundException;
import com.barberflow.cliente_service.model.Cliente;
import com.barberflow.cliente_service.repository.ClienteRepository;
import com.barberflow.cliente_service.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {

        cliente = new Cliente();

        cliente.setId(1L);
        cliente.setNombre("Juan Perez");
        cliente.setTelefono("987654321");
        cliente.setEmail("juan@test.com");

    }

    @Test
    void debeObtenerTodosLosClientes() {

        when(clienteRepository.findAll())
                .thenReturn(Arrays.asList(cliente));

        List<Cliente> resultado = clienteService.obtenerClientes();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void debeGuardarCliente() {

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        Cliente resultado = clienteService.guardarCliente(cliente);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void debeBuscarClientePorId() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado =
                clienteService.obtenerClientePorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Perez",
                resultado.get().getNombre());

        verify(clienteRepository, times(1))
                .findById(1L);
    }

    @Test
    void debeActualizarCliente() {

        Cliente actualizado = new Cliente();

        actualizado.setNombre("Pedro");
        actualizado.setTelefono("999999999");
        actualizado.setEmail("pedro@test.com");

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente resultado =
                clienteService.actualizarCliente(1L, actualizado);

        assertNotNull(resultado);

        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(any(Cliente.class));

    }

    @Test
    void debeEliminarCliente() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        clienteService.eliminarCliente(1L);

        verify(clienteRepository).delete(cliente);

    }

    @Test
    void debeLanzarExcepcionCuandoClienteNoExiste() {

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {

            clienteService.eliminarCliente(1L);

        });

    }

}