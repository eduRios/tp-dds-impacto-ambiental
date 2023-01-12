package com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos;


import com.dds.tpimpactoambiental.model.Localidad;

public class LocalidadDto extends BaseGeoApiDto {
    private String codPostal;

    public Localidad toLocalidad() {
        return new Localidad(id, nombre, codPostal);
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }
}
