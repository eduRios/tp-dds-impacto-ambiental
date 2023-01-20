package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.TipoCombustible;

import javax.persistence.*;

@Entity(name = "Combustible")
@Table(name = "combustibles")
public class Combustible extends BaseEntity {

    private TipoCombustible tipoCombustible;

    @ManyToOne
    @JoinColumn(
            name = "unidad",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Combustibles_Unidad")
    )
    private Unidad unidad;

    public Combustible() {
    }

    public Combustible(TipoCombustible tipoCombustible, Unidad unidad) {
        this.tipoCombustible = tipoCombustible;
        this.unidad = unidad;
    }

}
