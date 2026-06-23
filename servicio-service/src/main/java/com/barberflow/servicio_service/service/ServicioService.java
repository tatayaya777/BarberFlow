package com.barberflow.servicio_service.service;

import com.barberflow.servicio_service.model.Servicio;
import com.barberflow.servicio_service.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerServicios() {
        return servicioRepository.findAll();
    }

    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Optional<Servicio> obtenerServicioPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio actualizarServicio(Long id, Servicio servicioActualizado) {

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow();

        servicio.setNombre(servicioActualizado.getNombre());
        servicio.setPrecio(servicioActualizado.getPrecio());
        servicio.setDuracion(servicioActualizado.getDuracion());

        return servicioRepository.save(servicio);
    }

    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}