package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public Unidad() {
    }

    public Unidad(String simbolo, String nombre, double factorDeConversionAUnidadBase, boolean base) {
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
}
