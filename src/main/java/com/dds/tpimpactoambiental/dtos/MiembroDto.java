package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Miembro;
import com.dds.tpimpactoambiental.utils.DateTimeUtils;

public class MiembroDto extends BaseEntityDto {
    private IdTextPair persona;
    private IdTextPair usuario;
    private IdTextPair organizacion;
    private IdTextPair sector;
    private String fechaIngreso;
    private Long idSolicitud;

    public MiembroDto() {
    }

    private MiembroDto(Miembro miembro) {
        super(miembro);
    }

    public static MiembroDto from(Miembro miembro) {
        MiembroDto dto = new MiembroDto(miembro);
        dto.setPersona(new IdTextPair(miembro.getPersona()));
        if (miembro.getUsuario() != null) {
            dto.setUsuario(new IdTextPair(miembro.getUsuario()));
        }
        if (miembro.getSector() != null) {
            dto.setOrganizacion(new IdTextPair(miembro.getOrganizacion()));
            dto.setSector(new IdTextPair(miembro.getSector()));
        }
        if (miembro.getSolicitud() != null && miembro.getSolicitud().isActivo()) {
            dto.setIdSolicitud(miembro.getSolicitud().getId());
        } else {
            dto.setFechaIngreso(DateTimeUtils.dateToString(miembro.getFechaIngreso(), true));
        }
        return dto;
    }

    public IdTextPair getPersona() {
        return persona;
    }

    public void setPersona(IdTextPair persona) {
        this.persona = persona;
    }

    public IdTextPair getUsuario() {
        return usuario;
    }

    public void setUsuario(IdTextPair usuario) {
        this.usuario = usuario;
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

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
}
