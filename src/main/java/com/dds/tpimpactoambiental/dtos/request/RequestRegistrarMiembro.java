package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.IdTextPair;

public class RequestRegistrarMiembro {

    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;

    private IdTextPair persona;
    private IdTextPair organizacion;
    private IdTextPair sector;

    public RequestRegistrarMiembro() {
    }

    public RequestRegistrarMiembro(String nombre, String apellido, String tipoDocumento, String nroDocumento, IdTextPair persona, IdTextPair organizacion, IdTextPair sector) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.persona = persona;
        this.organizacion = organizacion;
        this.sector = sector;
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

    public IdTextPair getPersona() {
        return persona;
    }

    public void setPersona(IdTextPair persona) {
        this.persona = persona;
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
}
