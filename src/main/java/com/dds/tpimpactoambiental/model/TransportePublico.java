package com.dds.tpimpactoambiental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "transporte publico")
public class TransportePublico extends MedioTransporte{

    private String linea;

    @OneToMany(mappedBy = "transportePublico", cascade = CascadeType.ALL)
    private List<Parada> paradas = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "combustible",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TransportesPublicos_Combustible")
    )
    private Combustible combustible;

    private double combustibleConsumidoPorKm;

    public TransportePublico() {
    }

    public TransportePublico(String linea, Combustible combustible, double combustibleConsumidoPorKm) {
        this.linea = linea;
        this.combustible = combustible;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
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

    public void addParada(Parada parada) {
        paradas.add(parada);
        parada.setTransportePublico(this);
    }

    public void addParadas(List<Parada> paradas) {
        paradas.forEach(this::addParada);
    }

    @Override
    public String toString() {
        return "Linea " + linea + " (" + getTipoMedioDeTransporte().toString() + ")";
    }
}
