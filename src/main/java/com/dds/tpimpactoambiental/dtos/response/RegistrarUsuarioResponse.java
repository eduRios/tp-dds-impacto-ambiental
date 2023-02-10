package com.dds.tpimpactoambiental.dtos.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public class RegistrarUsuarioResponse extends Response {

    private List<String> errores;

    public RegistrarUsuarioResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public RegistrarUsuarioResponse(HttpStatus status, String message, List<String> errores) {
        this(status, message);
        this.errores = errores;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
