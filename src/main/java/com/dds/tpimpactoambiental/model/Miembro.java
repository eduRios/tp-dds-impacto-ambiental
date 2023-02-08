package com.dds.tpimpactoambiental.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="miembro")
public class Miembro extends BaseEntity{

    private LocalDate fechaIngreso;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "persona", nullable = false, foreignKey = @ForeignKey(name = "FK_Miembros_Persona"))
    private Persona persona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "FK_Miembros_Usuario"))
    private Usuario usuario;

    @OneToOne(mappedBy = "miembro")
    private Solicitud solicitud;
    @ManyToMany(mappedBy = "miembros")
    private List<Tramo> tramos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sector", foreignKey = @ForeignKey(name = "FK_Miembros_Sector"))
    private Sector sector;

    public Miembro() {
    }

    public Miembro(Persona persona) {
        this.persona = persona;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Organizacion getOrganizacion() {
        return sector.getOrganizacion();
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setMiembro(this);
    }


}
