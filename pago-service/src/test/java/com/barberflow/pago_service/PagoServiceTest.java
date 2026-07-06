package com.barberflow.pago_service;

import com.barberflow.pago_service.model.Pago;
import com.barberflow.pago_service.repository.PagoRepository;
import com.barberflow.pago_service.service.PagoService;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pago pago;

    @BeforeEach
    void setUp() {

        pago = new Pago();

        pago.setMonto(20000.0);
        pago.setMetodo("Efectivo");
        pago.setEstado("Pagado");

    }

    @Test
    void debeObtenerTodosLosPagos() {

        when(pagoRepository.findAll())
                .thenReturn(Arrays.asList(pago));

        List<Pago> resultado = pagoService.obtenerPagos();

        assertEquals(1, resultado.size());

        verify(pagoRepository).findAll();

    }

    @Test
    void debeGuardarPago() {

        when(pagoRepository.save(pago))
                .thenReturn(pago);

        Pago resultado = pagoService.guardarPago(pago);

        assertNotNull(resultado);
        assertEquals("Efectivo", resultado.getMetodo());

        verify(pagoRepository).save(pago);

    }

    @Test
    void debeBuscarPagoPorId() {

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pago));

        Optional<Pago> resultado =
                pagoService.obtenerPagoPorId(1L);

        assertTrue(resultado.isPresent());

        verify(pagoRepository).findById(1L);

    }

    @Test
    void debeActualizarPago() {

        Pago actualizado = new Pago();

        actualizado.setMonto(30000.0);
        actualizado.setMetodo("Transferencia");
        actualizado.setEstado("Pendiente");

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pago));

        when(pagoRepository.save(any(Pago.class)))
                .thenReturn(pago);

        Pago resultado =
                pagoService.actualizarPago(1L, actualizado);

        assertNotNull(resultado);

        verify(pagoRepository).findById(1L);
        verify(pagoRepository).save(any(Pago.class));

    }

    @Test
    void debeEliminarPago() {

        pagoService.eliminarPago(1L);

        verify(pagoRepository).deleteById(1L);

    }

}