package com.dds.tpimpactoambiental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "otro")
public class Otro extends MedioTransporte{

    String descripcion;

    public Otro() {
    }

    public Otro(String descripcion) {
        super();
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
