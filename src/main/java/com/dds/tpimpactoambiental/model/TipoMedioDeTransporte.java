package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TipoMedioDeTransporte")
@Table(name = "tipos_medio_de_transporte")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoMedioDeTransporte extends BaseEntity {

    private String nombre;

    @OneToOne(mappedBy = "tipoMedioDeTransporte", cascade = CascadeType.ALL)
    private FactorDeEmision factorDeEmision;

    @OneToMany(mappedBy = "tipoMedioDeTransporte", cascade = CascadeType.ALL)
    private List<MedioTransporte> mediosDeTransporte = new ArrayList<>();

    public TipoMedioDeTransporte() {
    }

    public TipoMedioDeTransporte(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FactorDeEmision getFactorDeEmision() {
        return factorDeEmision;
    }

    public List<MedioTransporte> getMediosDeTransporte() {
        return mediosDeTransporte;
    }

    public void setMediosDeTransporte(List<MedioTransporte> mediosDeTransporte) {
        this.mediosDeTransporte = mediosDeTransporte;
    }

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoMedioDeTransporte(this);
    }

    public void addMedioDeTransporte(MedioTransporte medioDeTransporte) {
        mediosDeTransporte.add(medioDeTransporte);
        medioDeTransporte.setTipoMedioDeTransporte(this);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
