package com.barberflow.barbero_service.service;

import com.barberflow.barbero_service.model.Barbero;
import com.barberflow.barbero_service.repository.BarberoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    public List<Barbero> obtenerBarberos() {
        return barberoRepository.findAll();
    }

    public Barbero guardarBarbero(Barbero barbero) {
        return barberoRepository.save(barbero);
    }

    public Optional<Barbero> obtenerBarberoPorId(Long id) {
        return barberoRepository.findById(id);
    }

    public Barbero actualizarBarbero(Long id, Barbero barberoActualizado) {

        Barbero barbero = barberoRepository.findById(id).orElseThrow();

        barbero.setNombre(barberoActualizado.getNombre());
        barbero.setEspecialidad(barberoActualizado.getEspecialidad());
        barbero.setEmail(barberoActualizado.getEmail());

        return barberoRepository.save(barbero);
    }

    public void eliminarBarbero(Long id) {
        barberoRepository.deleteById(id);
    }
}