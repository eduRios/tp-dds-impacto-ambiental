package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Unidad;

public class UnidadDto extends BaseEntityDto {
    private String nombre;
    private String simbolo;
    private String tipoUnidad;

    public UnidadDto() {
    }

    private UnidadDto(Unidad unidad) {
        super(unidad);
    }

    public static UnidadDto from(Unidad unidad) {
        UnidadDto dto = new UnidadDto(unidad);
        dto.setNombre(unidad.getNombre());
        dto.setSimbolo(unidad.getSimbolo());
        if (unidad.getTipoUnidad() != null)
            dto.setTipoUnidad(unidad.getTipoUnidad().getNombre());
        return dto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
}
