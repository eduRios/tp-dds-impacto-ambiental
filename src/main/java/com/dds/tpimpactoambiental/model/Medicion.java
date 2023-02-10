package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.Actividad;
import com.dds.tpimpactoambiental.enums.Periodicidad;
import com.dds.tpimpactoambiental.utils.DateTimeUtils;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Medicion")
@Table(name = "mediciones")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Medicion extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Mediciones_Organizacion"))
    private Organizacion organizacion;

    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "tipo_consumo", nullable = false, foreignKey = @ForeignKey(name = "FK_Mediciones_TipoConsumo"))
    private TipoConsumo tipoConsumo;

    private Periodicidad periodicidad;

    /**
     * Si la periodicidad es MENSUAL, entonces el periodoImputacion se guarda como 01/MM/YYYY (importan
     * el Mes y el Anio).<br>
     * Si la periodicidad es ANUAL, entonces el periodoImputacion se guarda como 01/01/YYYY (solo importa el Anio).
     */
    private LocalDate periodoImputacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor_numerico", foreignKey = @ForeignKey(name = "FK_Mediciones_ValorNumerico"))
    private Cantidad valorNumerico;

    private String valorString;

    @ManyToOne
    @JoinColumn(name = "tipo_medio_de_transporte",
            foreignKey = @ForeignKey(name = "FK_Mediciones_TipoMedioDeTransporte"))
    private TipoMedioDeTransporte tipoMedioDeTransporte;

    public Medicion() {
    }

    public Medicion(Actividad actividad, TipoConsumo tipoConsumo, Periodicidad periodicidad,
                    String periodoImputacion, String valor) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.periodicidad = periodicidad;
        this.periodoImputacion = DateTimeUtils.getDateFromString(periodoImputacion);

        if (tipoConsumo.tieneValorNumerico()) {
            Unidad unidad = tipoConsumo.getUnidad();
            this.valorNumerico = new Cantidad(unidad, Double.parseDouble(valor));
        } else {
            this.valorString = valor;
        }
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public TipoConsumo getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(TipoConsumo tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
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

    public Cantidad getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(Cantidad valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public TipoMedioDeTransporte getTipoMedioDeTransporte() {
        return tipoMedioDeTransporte;
    }

    public void setTipoMedioDeTransporte(TipoMedioDeTransporte tipoMedioDeTransporte) {
        this.tipoMedioDeTransporte = tipoMedioDeTransporte;
    }
}
