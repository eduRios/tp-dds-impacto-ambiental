package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.EspacioDto;

public class RequestCrearSector {
    private String nombre;
    private long idOrganizacion;
    private EspacioDto espacio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public EspacioDto getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDto espacio) {
        this.espacio = espacio;
    }
}
