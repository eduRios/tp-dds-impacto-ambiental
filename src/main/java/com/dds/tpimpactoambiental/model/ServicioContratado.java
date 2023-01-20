package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "servicio contratado")
public class ServicioContratado extends MedioTransporte{
    String nombreServicio;

    @ManyToOne
    @JoinColumn(
            name = "combustible",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_VehiculosParticulares_Combustible")
    )
    private Combustible combustible;

    private double combustibleConsumidoPorKm;

    public ServicioContratado() {
    }

    public ServicioContratado(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public ServicioContratado(String nombreServicio, Combustible combustible, double combustibleConsumidoPorKm) {
        this.nombreServicio = nombreServicio;
        this.combustible = combustible;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public double getCombustibleConsumidoPorKm() {
        return combustibleConsumidoPorKm;
    }

    public void setCombustibleConsumidoPorKm(double combustibleConsumidoPorKm) {
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }
}
