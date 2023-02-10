package com.dds.tpimpactoambiental.model;

import com.dds.tpimpactoambiental.enums.TipoSectorTerritorial;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SectorTerritorial")
@Table(name = "sectores_territoriales")
public class SectorTerritorial extends BaseEntity {

    private String nombre;

    @OneToMany(mappedBy = "sectorTerritorial")
    private List<AgenteSectorial> agentesSectoriales = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "organizaciones_por_sector_territorial",
            joinColumns = @JoinColumn(name = "sector_territorial"),
            inverseJoinColumns = @JoinColumn(name = "organizacion"),
            foreignKey = @ForeignKey(name = "FK_OrganizacionesPorSectorTerritorial_SectorTerritorial"),
            inverseForeignKey = @ForeignKey(name = "FK_OrganizacionesPorSectorTerritorial_Organizacion")
    )
    private List<Organizacion> organizaciones = new ArrayList<>();

    private TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial() {
    }

    public SectorTerritorial(String nombre, List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.nombre = nombre;
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipoSectorTerritorial = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AgenteSectorial> getAgentesSectoriales() {
        return agentesSectoriales;
    }

    public void setAgentesSectoriales(List<AgenteSectorial> agentesSectoriales) {
        this.agentesSectoriales = agentesSectoriales;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public TipoSectorTerritorial getTipoSectorTerritorial() {
        return tipoSectorTerritorial;
    }

    public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
