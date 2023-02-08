package com.dds.tpimpactoambiental.dtos.request;

import org.springframework.web.multipart.MultipartFile;

public class RequestCargarMediciones {

    private long idOrganizacion;
    private MultipartFile file;

    public RequestCargarMediciones() {
    }

    public RequestCargarMediciones(long idOrganizacion, MultipartFile file) {
        this.idOrganizacion = idOrganizacion;
        this.file = file;
    }

    public long getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
