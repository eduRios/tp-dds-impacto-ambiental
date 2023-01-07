package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.IdTextPair;

public class RequestCrearOrganizacion {

    private String razonSocial;
    private IdTextPair tipoOrganizacion;
    private IdTextPair clasificacion;

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public IdTextPair getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(IdTextPair tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public IdTextPair getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(IdTextPair clasificacion) {
        this.clasificacion = clasificacion;
    }
}
