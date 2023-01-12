package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="organizacion")
public class Organizacion extends BaseEntity{
    private String razonSocial;
    private String tipo;
    private String clasificacion;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Sector> sectores = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "organizacion_id")
    private List<DatosActividad> datosActividadList;
    public Organizacion() {
    }

    public Organizacion(String razonSocial, String tipo, String clasificacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
    }
    public String getRazonSocial() {
        return razonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }
    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores2) {
        this.sectores = sectores2;
    }

    public List<DatosActividad> getDatosActividadList() {
        return datosActividadList;
    }

    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }
}
