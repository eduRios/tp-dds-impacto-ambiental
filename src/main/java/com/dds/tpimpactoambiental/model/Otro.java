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

    @Override
    public double getCombustibleConsumidoPorKm() {
        // Un vehiculo no contaminante no usa combustible, asi que gasta siempre 0
        return 0;
    }

    @Override
    public String toString() {
        return getTipoMedioDeTransporte().toString();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
