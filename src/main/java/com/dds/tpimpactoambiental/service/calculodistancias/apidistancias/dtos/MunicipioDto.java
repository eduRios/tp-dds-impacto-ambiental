package com.dds.tpimpactoambiental.service.calculodistancias.apidistancias.dtos;


import com.dds.tpimpactoambiental.model.Municipio;

public class MunicipioDto extends BaseGeoApiDto {
    public Municipio toMunicipio() {
        return new Municipio(id, nombre);
    }

}
