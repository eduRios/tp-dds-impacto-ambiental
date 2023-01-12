package com.dds.tpimpactoambiental.dtos.request;

public class RequestAceptarSolicitud {

    private long idSolicitud;

    public RequestAceptarSolicitud() {
    }

    public RequestAceptarSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
}
