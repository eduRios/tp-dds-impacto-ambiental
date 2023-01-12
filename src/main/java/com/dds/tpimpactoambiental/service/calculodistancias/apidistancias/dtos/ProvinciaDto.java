package com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos;


import com.dds.tpimpactoambiental.model.Provincia;

public class ProvinciaDto extends BaseGeoApiDto {
    public Provincia toProvincia() {
        return new Provincia(id, nombre);
    }
}
