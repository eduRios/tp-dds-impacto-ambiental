package com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos;

import com.dds.tpimpactoambiental.model.Pais;

public class PaisDto extends BaseGeoApiDto {
    public Pais toPais() {
        return new Pais(id, nombre);
    }
}
