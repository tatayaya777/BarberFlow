package com.barberflow.reserva_service.service;

import com.barberflow.reserva_service.model.Reserva;
import com.barberflow.reserva_service.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerReservas() {
        return reservaRepository.findAll();
    }

    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva actualizarReserva(Long id, Reserva reservaActualizada) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow();

        reserva.setFecha(reservaActualizada.getFecha());
        reserva.setHora(reservaActualizada.getHora());
        reserva.setEstado(reservaActualizada.getEstado());

        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
