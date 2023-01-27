package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "Unidad")
@Table(name = "unidades")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Unidad extends BaseEntity{

    private String simbolo;
    private String nombre;
    private double factorDeConversionAUnidadBase;

    @Column(name = "es_base")
    private boolean base;

    @ManyToOne
    @JoinColumn(
            name = "tipo_unidad",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Unidades_TipoUnidad")
    )
    private TipoUnidad tipoUnidad;

    public Unidad() {
    }

    public Unidad(String simbolo, String nombre, boolean base, double factorDeConversionAUnidadBase) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.factorDeConversionAUnidadBase = factorDeConversionAUnidadBase;
        this.base = base;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFactorDeConversionAUnidadBase() {
        return factorDeConversionAUnidadBase;
    }

    public void setFactorDeConversionAUnidadBase(double factorDeConversionAUnidadBase) {
        this.factorDeConversionAUnidadBase = factorDeConversionAUnidadBase;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    public double getFactorDeConversionDesdeUnidadBase() {
        return 1 / factorDeConversionAUnidadBase;
    }

    public TipoUnidad getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(TipoUnidad tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
}
