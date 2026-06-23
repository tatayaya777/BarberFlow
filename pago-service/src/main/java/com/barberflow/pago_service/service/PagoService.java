package com.barberflow.pago_service.service;

import com.barberflow.pago_service.model.Pago;
import com.barberflow.pago_service.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> obtenerPagos() {
        return pagoRepository.findAll();
    }

    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Optional<Pago> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago actualizarPago(Long id, Pago pagoActualizado) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow();

        pago.setMonto(pagoActualizado.getMonto());
        pago.setMetodo(pagoActualizado.getMetodo());
        pago.setEstado(pagoActualizado.getEstado());

        return pagoRepository.save(pago);
    }

    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}