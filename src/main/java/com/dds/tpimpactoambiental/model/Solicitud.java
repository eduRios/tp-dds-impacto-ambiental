package com.dds.tpimpactoambiental.model;

import javax.persistence.*;

@Entity
public class Solicitud extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "miembro", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Miembro"))
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Sector"))
    private Sector sector;

    public Solicitud() {
    }

    public Solicitud(Miembro miembro, Sector sector) {
        this.miembro = miembro;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "{" +
                "miembro=" + miembro.toString() +
                ", sector=" + sector.toString() +
                '}';
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
