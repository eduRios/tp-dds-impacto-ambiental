package com.dds.tpimpactoambiental.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "transporte publico")
public class TransportePublico extends MedioTransporte{
    String tipoTransporte;
    String linea;

    public TransportePublico() {
    }

    public TransportePublico(String tipoTransporte, String linea) {
        this.tipoTransporte = tipoTransporte;
        this.linea = linea;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
}
