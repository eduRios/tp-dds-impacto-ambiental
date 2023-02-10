package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.IdTextPair;


public class RequestCrearPersona {
    private String nombre;
    private String apellido;
    private IdTextPair tipoDocumento;
    private String documento;

    public RequestCrearPersona() {
    }

    public RequestCrearPersona(String nombre, String apellido, IdTextPair tipoDocumento, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
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

    public IdTextPair getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(IdTextPair tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
