package com.barberflow.reporte_service.service;

import com.barberflow.reporte_service.model.Reporte;
import com.barberflow.reporte_service.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> obtenerReportes() {
        return reporteRepository.findAll();
    }

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Optional<Reporte> obtenerReportePorId(Long id) {
        return reporteRepository.findById(id);
    }

    public Reporte actualizarReporte(Long id, Reporte reporteActualizado) {

        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow();

        reporte.setFecha(reporteActualizado.getFecha());
        reporte.setIngresos(reporteActualizado.getIngresos());
        reporte.setReservas(reporteActualizado.getReservas());

        return reporteRepository.save(reporte);
    }

    public void eliminarReporte(Long id) {
        reporteRepository.deleteById(id);
    }
}