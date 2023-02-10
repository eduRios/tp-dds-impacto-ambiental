package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.Periodicidad;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "RegistroCalculoHCDatoActividad")
@Table(name = "registros_calculo_hc_dato_actividad")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RegistroCalculoHCDatoActividad extends BaseEntity {

    private Periodicidad periodicidad;
    private LocalDate periodoImputacion;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCDatoActividad_Organizacion"))
    private Organizacion organizacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCDatoActividad_Valor"))
    private Cantidad valor;

    public RegistroCalculoHCDatoActividad() {
    }

    public RegistroCalculoHCDatoActividad(Periodicidad periodicidad, LocalDate periodoImputacion, Cantidad valor) {
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
        this.valor = valor;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public LocalDate getPeriodoImputacion() {
        return periodoImputacion;
    }

    public void setPeriodoImputacion(LocalDate periodoImputacion) {
        this.periodoImputacion = periodoImputacion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Cantidad getValor() {
        return valor;
    }

    public void setValor(Cantidad valor) {
        this.valor = valor;
    }
}
