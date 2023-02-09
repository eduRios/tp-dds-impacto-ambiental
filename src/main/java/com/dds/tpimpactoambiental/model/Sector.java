package com.dds.tpimpactoambiental.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Sector extends BaseEntity{

    private String nombre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "organizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Sectores_Organizacion"))
    private Organizacion organizacion;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "espacio",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Sectores_Espacio")
    )
    private Espacio espacio;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    private List<Miembro> miembros = new ArrayList<>();

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solicitud> solicitudes = new ArrayList<>();

    public Sector() {
    }

    public Sector(String nombre, Organizacion organizacion, Espacio espacio) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.espacio = espacio;
    }

    public void addSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
        solicitud.setSector(this);
    }

    public void aceptarSolicitud(Solicitud solicitud) {
        this.addMiembro(solicitud.getMiembro());
        solicitudes.remove(solicitud);
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.setSector(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return nombre + " (Org: " + organizacion.toString() + ")";
    }
}
