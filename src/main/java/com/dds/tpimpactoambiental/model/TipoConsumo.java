package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.Actividad;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "TipoConsumo")
@Table(name = "tipos_de_consumo")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoConsumo extends BaseEntity {
    private String nombre;
    private Actividad actividad;
    private String alcance;

    @ManyToOne
    @JoinColumn(name = "unidad", foreignKey = @ForeignKey(name = "FK_TiposDeConsumo_Unidad"))
    private Unidad unidad;

    @OneToOne(mappedBy = "tipoConsumo", cascade = CascadeType.ALL)
    private FactorDeEmision factorDeEmision;

    public TipoConsumo() {
    }

    public TipoConsumo(String nombre, Actividad actividad, String alcance, Unidad unidad) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.alcance = alcance;
        this.unidad = unidad;
    }

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoConsumo(this);
    }

    public boolean tieneValorNumerico() {
        // Si el TipoConsumo no tiene unidad, es porque su valor no se mide con numeros
        return unidad != null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public FactorDeEmision getFactorDeEmision() {
        return factorDeEmision;
    }
}
