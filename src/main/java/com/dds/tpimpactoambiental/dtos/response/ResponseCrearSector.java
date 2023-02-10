package com.dds.tpimpactoambiental.dtos.response;

public class ResponseCrearSector extends Response{
    private long idSector;

    public ResponseCrearSector() {
    }

    public ResponseCrearSector(String message, long idSector) {
        super(message);
        this.idSector = idSector;
    }

    public long getIdSector() {
        return idSector;
    }

    public void setIdSector(long idSector) {
        this.idSector = idSector;
    }
}
