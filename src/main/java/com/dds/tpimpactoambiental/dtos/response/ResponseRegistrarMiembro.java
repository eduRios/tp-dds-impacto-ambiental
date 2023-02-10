package com.dds.tpimpactoambiental.dtos.response;

public class ResponseRegistrarMiembro extends Response{
    private long idSolicitud;

    public ResponseRegistrarMiembro(String message, long idSolicitud) {
        super(message);
        this.idSolicitud = idSolicitud;
    }

    public ResponseRegistrarMiembro() {
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
}
