package com.dds.tpimpactoambiental.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="organizacion")
public class Organizacion extends BaseEntity{
    private String razonSocial;
    private String tipo;
    private String clasificacion;

    private int cantDiasHabilesPorSemana;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factor_k", nullable = true, foreignKey = @ForeignKey(name = "FK_Organizaciones_FactorK"))
    private Cantidad factorK;

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

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getCantDiasHabilesPorSemana() {
        return cantDiasHabilesPorSemana;
    }

    public void setCantDiasHabilesPorSemana(int cantDiasHabilesPorSemana) {
        this.cantDiasHabilesPorSemana = cantDiasHabilesPorSemana;
    }

    public Cantidad getFactorK() {
        return factorK;
    }

    public void setFactorK(Cantidad factorK) {
        this.factorK = factorK;
    }

    public void setDatosActividadList(List<DatosActividad> datosActividadList) {
        this.datosActividadList = datosActividadList;
    }

    public List<Miembro> getMiembros() { // Para saber los miembros que tiene una organizacion de cada sector que tiene
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
