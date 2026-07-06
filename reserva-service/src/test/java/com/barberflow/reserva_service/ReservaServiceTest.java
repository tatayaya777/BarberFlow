package com.barberflow.reserva_service;

import com.barberflow.reserva_service.exception.ResourceNotFoundException;
import com.barberflow.reserva_service.model.Reserva;
import com.barberflow.reserva_service.repository.ReservaRepository;
import com.barberflow.reserva_service.service.ReservaService;

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
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Reserva reserva;

    @BeforeEach
    void setUp() {

        reserva = new Reserva();

        reserva.setFecha("2026-07-10");
        reserva.setHora("18:00");
        reserva.setEstado("Pendiente");

    }

    @Test
    void debeObtenerTodasLasReservas() {

        when(reservaRepository.findAll())
                .thenReturn(Arrays.asList(reserva));

        List<Reserva> resultado =
                reservaService.obtenerReservas();

        assertEquals(1, resultado.size());

        verify(reservaRepository).findAll();

    }

    @Test
    void debeGuardarReserva() {

        when(reservaRepository.save(reserva))
                .thenReturn(reserva);

        Reserva resultado =
                reservaService.guardarReserva(reserva);

        assertNotNull(resultado);
        assertEquals("Pendiente", resultado.getEstado());

        verify(reservaRepository).save(reserva);

    }

    @Test
    void debeBuscarReservaPorId() {

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reserva));

        Optional<Reserva> resultado =
                reservaService.obtenerReservaPorId(1L);

        assertTrue(resultado.isPresent());

        verify(reservaRepository).findById(1L);

    }

    @Test
    void debeActualizarReserva() {

        Reserva actualizada = new Reserva();

        actualizada.setFecha("2026-07-12");
        actualizada.setHora("20:00");
        actualizada.setEstado("Confirmada");

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reserva));

        when(reservaRepository.save(any(Reserva.class)))
                .thenReturn(reserva);

        Reserva resultado =
                reservaService.actualizarReserva(1L, actualizada);

        assertNotNull(resultado);

        verify(reservaRepository).findById(1L);
        verify(reservaRepository).save(any(Reserva.class));

    }

    @Test
    void debeEliminarReserva() {

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reserva));

        reservaService.eliminarReserva(1L);

        verify(reservaRepository).delete(reserva);

    }

    @Test
    void debeLanzarExcepcionCuandoReservaNoExiste() {

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {

            reservaService.eliminarReserva(1L);

        });

    }

}