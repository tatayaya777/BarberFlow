package com.barberflow.notificacion_service.service;

import com.barberflow.notificacion_service.model.Notificacion;
import com.barberflow.notificacion_service.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private static final Logger logger =
        LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Optional<Notificacion> obtenerNotificacionPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion actualizada) {

        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow();

        notificacion.setMensaje(actualizada.getMensaje());
        notificacion.setDestinatario(actualizada.getDestinatario());

        return notificacionRepository.save(notificacion);
    }

    public void eliminarNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}