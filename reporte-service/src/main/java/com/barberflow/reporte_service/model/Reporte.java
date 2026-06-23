package com.barberflow.reporte_service.model;

import jakarta.persistence.*;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;

    private Double ingresos;

    private Integer reservas;

    public Reporte() {
    }

    public Long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }

    public Integer getReservas() {
        return reservas;
    }

    public void setReservas(Integer reservas) {
        this.reservas = reservas;
    }
}