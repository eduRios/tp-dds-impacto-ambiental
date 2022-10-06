package com.dds.tpimpactoambiental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "servicio contratado")
public class ServicioContratado extends MedioTransporte{
    String nombreServicio;

    public ServicioContratado() {
    }

    public ServicioContratado(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
