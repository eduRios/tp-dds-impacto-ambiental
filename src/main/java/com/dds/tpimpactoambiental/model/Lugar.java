package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity(name = "Lugar")
@Table(name = "lugares")
@Inheritance(strategy = InheritanceType.JOINED)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Lugar extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "direccion", nullable = false, foreignKey = @ForeignKey(name = "FK_Lugares_Direccion"))
    protected Direccion direccion;

    protected Lugar() {
    }

    public Lugar(Direccion direccion) {
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
