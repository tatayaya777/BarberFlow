package com.barberflow.sucursal_service.service;

import com.barberflow.sucursal_service.model.Sucursal;
import com.barberflow.sucursal_service.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalService {
    private static final Logger logger =
        LoggerFactory.getLogger(SucursalService.class);
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> obtenerSucursales() {
        return sucursalRepository.findAll();
    }

    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Optional<Sucursal> obtenerSucursalPorId(Long id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal actualizarSucursal(Long id, Sucursal sucursalActualizada) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow();

        sucursal.setNombre(sucursalActualizada.getNombre());
        sucursal.setDireccion(sucursalActualizada.getDireccion());
        sucursal.setTelefono(sucursalActualizada.getTelefono());

        return sucursalRepository.save(sucursal);
    }

    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }
}