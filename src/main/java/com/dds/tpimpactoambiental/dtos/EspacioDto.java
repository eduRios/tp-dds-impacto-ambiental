package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Espacio;

public class EspacioDto extends BaseEntityDto{

    private String nombre;
    private String tipoEspacio;
    private DireccionDto direccion;

    public EspacioDto() {
    }
    private EspacioDto(Espacio espacio) {
        super(espacio);
    }

    public static EspacioDto from(Espacio espacio) {
        EspacioDto dto = new EspacioDto(espacio);
        dto.setNombre(espacio.getNombre());
        dto.setTipoEspacio(espacio.getTipoEspacio().toString());
        if (espacio.getDireccion() != null) {
            dto.setDireccion(DireccionDto.from(espacio.getDireccion()));
        }
        return dto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public DireccionDto getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDto direccion) {
        this.direccion = direccion;
    }
}
