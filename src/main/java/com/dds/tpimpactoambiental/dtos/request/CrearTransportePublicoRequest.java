package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.dtos.ParadaDto;

import java.util.ArrayList;
import java.util.List;

public class CrearTransportePublicoRequest {
    private String linea;
    private IdTextPair tipoMedioDeTransporte;
    private long idCombustible;
    private double combustibleConsumidoPorKm;
    // FactorDeEmision???
    private List<ParadaDto> paradas = new ArrayList<>();

    public CrearTransportePublicoRequest(String linea, IdTextPair tipoMedioDeTransporte, long idCombustible, double combustibleConsumidoPorKm, List<ParadaDto> paradas) {
        this.linea = linea;
        this.tipoMedioDeTransporte = tipoMedioDeTransporte;
        this.idCombustible = idCombustible;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
        this.paradas = paradas;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public IdTextPair getTipoMedioDeTransporte() {
        return tipoMedioDeTransporte;
    }

    public void setTipoMedioDeTransporte(IdTextPair tipoMedioDeTransporte) {
        this.tipoMedioDeTransporte = tipoMedioDeTransporte;
    }

    public long getIdCombustible() {
        return idCombustible;
    }

    public void setIdCombustible(long idCombustible) {
        this.idCombustible = idCombustible;
    }

    public double getCombustibleConsumidoPorKm() {
        return combustibleConsumidoPorKm;
    }

    public void setCombustibleConsumidoPorKm(double combustibleConsumidoPorKm) {
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    public List<ParadaDto> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadaDto> paradas) {
        this.paradas = paradas;
    }
}
