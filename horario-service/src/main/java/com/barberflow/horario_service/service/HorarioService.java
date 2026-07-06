package com.barberflow.horario_service.service;

import com.barberflow.horario_service.model.Horario;
import com.barberflow.horario_service.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> obtenerHorarios() {
        return horarioRepository.findAll();
    }

    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Optional<Horario> obtenerHorarioPorId(Long id) {
        return horarioRepository.findById(id);
    }

    public Horario actualizarHorario(Long id, Horario horarioActualizado) {

        Horario horario = horarioRepository.findById(id)
                .orElseThrow();

        horario.setDia(horarioActualizado.getDia());
        horario.setHora(horarioActualizado.getHora());

        return horarioRepository.save(horario);
    }

    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}