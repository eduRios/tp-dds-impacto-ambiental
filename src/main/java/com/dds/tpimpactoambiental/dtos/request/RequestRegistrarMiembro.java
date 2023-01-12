package com.dds.tpimpactoambiental.dtos.request;

public class RequestRegistrarMiembro {

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;
    private long idOrganizacion;
    private long idSector;

    public RequestRegistrarMiembro() {
    }

    public RequestRegistrarMiembro(String nombre, String apellido, String tipoDocumento, String nroDocumento, long idOrganizacion, long idSector) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.idOrganizacion = idOrganizacion;
        this.idSector = idSector;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public long getIdSector() {
        return idSector;
    }

    public void setIdSector(long idSector) {
        this.idSector = idSector;
    }
}
