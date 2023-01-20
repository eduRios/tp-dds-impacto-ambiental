package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="medio_transporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.STRING)
public abstract class MedioTransporte extends BaseEntity{

    @OneToOne
    private Tramo tramo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "tipo_medio_de_transporte",
            nullable = true,
            foreignKey = @ForeignKey(name = "FK_MediosDeTransporte_TipoMedioDeTransporte")
    )
    private TipoMedioDeTransporte tipoMedioDeTransporte;

    public FactorDeEmision getFactorDeEmision() {
        return tipoMedioDeTransporte.getFactorDeEmision();
    }
    public MedioTransporte() {
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        this.tramo = tramo;
    }

    public TipoMedioDeTransporte getTipoMedioDeTransporte() {
        return tipoMedioDeTransporte;
    }

    public void setTipoMedioDeTransporte(TipoMedioDeTransporte tipoMedioDeTransporte) {
        this.tipoMedioDeTransporte = tipoMedioDeTransporte;
    }

    public abstract double getCombustibleConsumidoPorKm();
}
