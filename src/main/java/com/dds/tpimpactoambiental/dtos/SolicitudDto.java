package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Solicitud;

public class SolicitudDto extends BaseEntityDto {
    private IdTextPair miembro;
    private IdTextPair organizacion;
    private IdTextPair sector;
    private boolean activo;

    private SolicitudDto(Solicitud solicitud) {
        super(solicitud);
    }

    public static SolicitudDto from(Solicitud solicitud) {
        SolicitudDto dto = new SolicitudDto(solicitud);
        dto.setMiembro(new IdTextPair(solicitud.getMiembro()));
        dto.setOrganizacion(new IdTextPair(solicitud.getSector().getOrganizacion()));
        dto.setSector(new IdTextPair(solicitud.getSector()));
        dto.setActivo(solicitud.isActivo());
        return dto;
    }

    public SolicitudDto() {
    }

    public IdTextPair getMiembro() {
        return miembro;
    }

    public void setMiembro(IdTextPair miembro) {
        this.miembro = miembro;
    }

    public IdTextPair getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(IdTextPair organizacion) {
        this.organizacion = organizacion;
    }

    public IdTextPair getSector() {
        return sector;
    }

    public void setSector(IdTextPair sector) {
        this.sector = sector;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
