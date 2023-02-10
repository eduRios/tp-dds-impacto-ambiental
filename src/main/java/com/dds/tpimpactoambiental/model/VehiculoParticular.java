package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "vehiculo particular")
public class VehiculoParticular extends MedioTransporte{
    String tipoVeiculo;

    @ManyToOne
    @JoinColumn(
            name = "combustible",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_VehiculosParticulares_Combustible")
    )
    private Combustible combustible;

    private double combustibleConsumidoPorKm;

    public VehiculoParticular() {
    }


    public VehiculoParticular(String tipoVeiculo, Combustible combustible, double combustibleConsumidoPorKm) {
        this.tipoVeiculo = tipoVeiculo;
        this.combustible = combustible;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
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
