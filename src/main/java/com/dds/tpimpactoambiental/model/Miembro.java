package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="miembro")
public class Miembro extends BaseEntity{

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;

    private LocalDate fechaIngreso;

    @OneToOne(mappedBy = "miembro")
    private Solicitud solicitud;
    @ManyToMany(mappedBy = "miembros")
    private List<Tramo> tramos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sector", foreignKey = @ForeignKey(name = "FK_Miembros_Sector"))
    private Sector sector;

    public Miembro() {
    }

    public Miembro(String nombre, String apellido, String tipoDocumento, String nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
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

}
