package com.barberflow.reserva_service.service;

import com.barberflow.reserva_service.exception.ResourceNotFoundException;
import com.barberflow.reserva_service.model.Reserva;
import com.barberflow.reserva_service.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerReservas() {

        logger.info("Obteniendo lista de reservas.");

        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {

        logger.info("Buscando reserva con ID: {}", id);

        Optional<Reserva> reserva = reservaRepository.findById(id);

        if (reserva.isEmpty()) {
            logger.warn("No se encontró la reserva con ID: {}", id);
        }

        return reserva;
    }

    public Reserva guardarReserva(Reserva reserva) {

        logger.info("Creando nueva reserva.");

        Reserva nuevaReserva = reservaRepository.save(reserva);

        logger.info("Reserva creada correctamente con ID: {}", nuevaReserva.getId());

        return nuevaReserva;
    }

    public Reserva actualizarReserva(Long id, Reserva reservaActualizada) {

        logger.info("Actualizando reserva con ID: {}", id);

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        reserva.setFecha(reservaActualizada.getFecha());
        reserva.setHora(reservaActualizada.getHora());
        reserva.setEstado(reservaActualizada.getEstado());

        logger.info("Reserva actualizada correctamente.");

        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {

        logger.info("Eliminando reserva con ID: {}", id);

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reserva no encontrada con ID: " + id));

        reservaRepository.delete(reserva);

        logger.info("Reserva eliminada correctamente.");
    }

}