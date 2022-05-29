package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizacion_id")
    private Organizacion organizacion;

    public Long getId() {
        return id;
    }
    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Matricula() {
    }

    public Matricula(Miembro miembro, Organizacion organizacion) {
        this.miembro = miembro;
        this.organizacion = organizacion;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }
}
