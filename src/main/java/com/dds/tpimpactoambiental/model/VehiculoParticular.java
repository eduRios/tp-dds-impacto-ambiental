package com.dds.tpimpactoambiental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "vehiculo particular")
public class VehiculoParticular extends MedioTransporte{
    String tipoVeiculo;
    String tipoConbustible;

    public VehiculoParticular() {
    }

    public VehiculoParticular(String tipoVeiculo, String tipoConbustible) {
        this.tipoVeiculo = tipoVeiculo;
        this.tipoConbustible = tipoConbustible;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoConbustible() {
        return tipoConbustible;
    }

    public void setTipoConbustible(String tipoConbustible) {
        this.tipoConbustible = tipoConbustible;
    }
}
