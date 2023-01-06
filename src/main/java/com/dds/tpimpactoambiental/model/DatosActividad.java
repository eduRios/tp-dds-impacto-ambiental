package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class DatosActividad {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String actividad;
    private String tipoConsumo;
    private long valor;
    private String periocidad;
    private String periodoImputacion;


    public DatosActividad() {
    }

    public DatosActividad(String actividad, String tipoConsumo, long valor, String periocidad, String periodoImputacion) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valor = valor;
        this.periocidad = periocidad;
        this.periodoImputacion = periodoImputacion;
    }

    public Long getId() {
        return id;
    }

    public String getActividad() {
        return actividad;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public long getValor() {
        return valor;
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public String getPeriodoImputacion() {
        return periodoImputacion;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public void setPeriodoImputacion(String periodoImputacion) {
        this.periodoImputacion = periodoImputacion;
    }
}
