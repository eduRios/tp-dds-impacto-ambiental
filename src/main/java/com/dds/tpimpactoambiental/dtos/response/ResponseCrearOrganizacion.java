package com.dds.tpimpactoambiental.dtos.response;

public class ResponseCrearOrganizacion extends Response{

    private long idOrganizacion;

    public ResponseCrearOrganizacion() {
    }

    public ResponseCrearOrganizacion(String message, long idOrganizacion) {
        super(message);
        this.idOrganizacion = idOrganizacion;
    }

    public long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }
}
