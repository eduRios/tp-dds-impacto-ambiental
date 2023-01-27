package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "FactorDeEmision")
@Table(name = "factores_de_emision")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FactorDeEmision extends BaseEntity{

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "tipo_medio_de_transporte",
            foreignKey = @ForeignKey(name = "FK_FactoresDeEmision_TipoMedioDeTransporte")
    )
    private TipoMedioDeTransporte tipoMedioDeTransporte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cantidad", nullable = false, foreignKey = @ForeignKey(name = "FK_FactoresDeEmision_Cantidad"))
    private Cantidad cantidad;

    public FactorDeEmision() {
    }

    public FactorDeEmision(Cantidad cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMedioDeTransporte getTipoMedioDeTransporte() {
        return tipoMedioDeTransporte;
    }

    public void setTipoMedioDeTransporte(TipoMedioDeTransporte tipoMedioDeTransporte) {
        this.tipoMedioDeTransporte = tipoMedioDeTransporte;
    }

    public Cantidad getCantidad() {
        return cantidad;
    }

    public void setCantidad(Cantidad cantidad) {
        this.cantidad = cantidad;
    }
}
