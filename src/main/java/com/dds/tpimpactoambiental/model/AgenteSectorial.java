package com.dds.tpimpactoambiental.model;
import javax.persistence.*;

@Entity(name = "AgenteSectorial")
@Table(name = "agentes_sectoriales")
public class AgenteSectorial extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sector_territorial", nullable = false,
            foreignKey = @ForeignKey(name = "FK_AgentesSectoriales_SectorTerritorial"))
    private SectorTerritorial sectorTerritorial;

    private String nombre;
    private String apellido;

    public AgenteSectorial() {
    }

    public AgenteSectorial(SectorTerritorial sectorTerritorial, String nombre, String apellido) {
        this.sectorTerritorial = sectorTerritorial;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public SectorTerritorial getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
