package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Sector;
import com.dds.tpimpactoambiental.utils.ListUtils;

import java.util.List;

public class SectorDto extends BaseEntityDto {
    private String nombre;
    private IdTextPair organizacion;
    private IdTextPair espacio;
    private List<IdTextPair> miembros;
    private List<IdTextPair> solicitudes;

    public SectorDto() {
    }

    private SectorDto(Sector sector) {
        super(sector);
    }

    public static SectorDto from(Sector sector) {
        SectorDto dto = new SectorDto(sector);
        dto.setNombre(sector.getNombre());
        dto.setOrganizacion(new IdTextPair(sector.getOrganizacion()));
        dto.setEspacio(new IdTextPair(sector.getEspacio()));
        dto.setMiembros(ListUtils.toIdTextPairList(sector.getMiembros()));
        dto.setSolicitudes(ListUtils.toIdTextPairList(sector.getSolicitudes()));
        return dto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public IdTextPair getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(IdTextPair organizacion) {
        this.organizacion = organizacion;
    }

    public IdTextPair getEspacio() {
        return espacio;
    }

    public void setEspacio(IdTextPair espacio) {
        this.espacio = espacio;
    }

    public List<IdTextPair> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<IdTextPair> miembros) {
        this.miembros = miembros;
    }

    public List<IdTextPair> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<IdTextPair> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
