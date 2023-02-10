package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "Unidad")
@Table(name = "unidades")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Unidad extends BaseEntity {

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

    @OneToMany(mappedBy = "unidadIzquierda", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesEnIzquierda = new ArrayList<>();

    @OneToMany(mappedBy = "unidadDerecha", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesEnDerecha = new ArrayList<>();

    @OneToMany(mappedBy = "unidadProducto", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesProducto = new ArrayList<>();

    @OneToMany(mappedBy = "unidadCociente", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesCociente = new ArrayList<>();

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

    public TipoUnidad getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(TipoUnidad tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public List<RelacionUnidades> getRelacionesUnidadesEnIzquierda() {
        return relacionesUnidadesEnIzquierda;
    }

    public void setRelacionesUnidadesEnIzquierda(List<RelacionUnidades> relacionesUnidadesEnIzquierda) {
        this.relacionesUnidadesEnIzquierda = relacionesUnidadesEnIzquierda;
    }

    public List<RelacionUnidades> getRelacionesUnidadesEnDerecha() {
        return relacionesUnidadesEnDerecha;
    }

    public void setRelacionesUnidadesEnDerecha(List<RelacionUnidades> relacionesUnidadesEnDerecha) {
        this.relacionesUnidadesEnDerecha = relacionesUnidadesEnDerecha;
    }

    public List<RelacionUnidades> getRelacionesUnidadesProducto() {
        return relacionesUnidadesProducto;
    }

    public void setRelacionesUnidadesProducto(List<RelacionUnidades> relacionesUnidadesProducto) {
        this.relacionesUnidadesProducto = relacionesUnidadesProducto;
    }

    public List<RelacionUnidades> getRelacionesUnidadesCociente() {
        return relacionesUnidadesCociente;
    }

    public void setRelacionesUnidadesCociente(List<RelacionUnidades> relacionesUnidadesCociente) {
        this.relacionesUnidadesCociente = relacionesUnidadesCociente;
    }

    public static String toString(Unidad unidad) {
        if (unidad == null)
            return "-";
        return unidad.toString();
    }

    public double getFactorDeConversionDesdeUnidadBase() {
        return 1 / factorDeConversionAUnidadBase;
    }

    public List<RelacionUnidades> getAllRelacionesUnidades() {
        return Stream.concat(
                relacionesUnidadesEnIzquierda.stream(),
                Stream.concat(
                        relacionesUnidadesEnDerecha.stream(),
                        Stream.concat(
                                relacionesUnidadesProducto.stream(),
                                relacionesUnidadesCociente.stream()
                        )
                )
        ).collect(Collectors.toList());
    }

    public void addRelacionEnIzquierda(RelacionUnidades relacionUnidades) {
        relacionesUnidadesEnIzquierda.add(relacionUnidades);
        relacionUnidades.setUnidadIzquierda(this);
    }

    public void addRelacionEnDerecha(RelacionUnidades relacionUnidades) {
        relacionesUnidadesEnDerecha.add(relacionUnidades);
        relacionUnidades.setUnidadDerecha(this);
    }

    public void addRelacionEnProducto(RelacionUnidades relacionUnidades) {
        relacionesUnidadesProducto.add(relacionUnidades);
        relacionUnidades.setUnidadProducto(this);
    }

    public void addRelacionEnCociente(RelacionUnidades relacionUnidades) {
        relacionesUnidadesCociente.add(relacionUnidades);
        relacionUnidades.setUnidadCociente(this);
    }

    @Override
    public String toString() {
        return simbolo;
    }

}