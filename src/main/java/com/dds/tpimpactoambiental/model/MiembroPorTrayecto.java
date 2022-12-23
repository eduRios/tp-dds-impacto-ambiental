package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "MiembroPorTrayecto")
@Table(name = "miembros_por_trayecto")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MiembroPorTrayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false, foreignKey = @ForeignKey(name = "FK_MiembrosPorTrayecto_Miembro"))
    private Miembro miembro;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "trayecto", nullable = false, foreignKey = @ForeignKey(name = "FK_MiembrosPorTrayecto_Trayecto"))
    private Trayecto trayecto;

    private double peso;

    public MiembroPorTrayecto() {
    }

    public MiembroPorTrayecto(Miembro miembro, Trayecto trayecto, double peso) {
        this.miembro = miembro;
        this.trayecto = trayecto;
        this.peso = peso;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
