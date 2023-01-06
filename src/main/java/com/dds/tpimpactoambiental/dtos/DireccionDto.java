package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.model.Direccion;
import com.dds.tpimpactoambiental.model.Localidad;
import com.dds.tpimpactoambiental.model.Municipio;

public class DireccionDto extends BaseEntityDto {
    private String calle;
    private String altura;
    private IdTextPair pais;
    private IdTextPair provincia;
    private IdTextPair municipio;
    private IdTextPair localidad;
    private String codigoPostal;

    public DireccionDto() {
    }
    private DireccionDto(Direccion direccion) {
        super(direccion);
    }

    public static DireccionDto from(Direccion direccion) {
        DireccionDto dto = new DireccionDto(direccion);
        dto.setCalle(direccion.getCalle());
        dto.setAltura(direccion.getAltura());
        dto.setPais(new IdTextPair(direccion.getPais()));
        dto.setProvincia(new IdTextPair(direccion.getProvincia()));
        dto.setMunicipio(new IdTextPair(direccion.getMunicipio()));
        dto.setLocalidad(new IdTextPair(direccion.getLocalidad()));
        dto.setCodigoPostal(direccion.getCodigoPostal());
        return dto;
    }
/*
    public static Direccion from(DireccionDto direccion) {
        Direccion dto = new Direccion();
        dto.setCalle(direccion.getCalle());
        dto.setAltura(direccion.getAltura());
//        dto.setPais(new IdTextPair(direccion.getPais()));
//        dto.setProvincia(new IdTextPair(direccion.getProvincia()));
//        dto.setMunicipio(new IdTextPair(direccion.getMunicipio()));
        dto.setLocalidad(new Localidad());
        Localidad localidad1 = new Localidad();
        localidad1.setCodigoPostal(direccion.getCodigoPostal());
        localidad1.setMunicipio();
        Municipio municipio1 = new Municipio();
        return null;
    }*/

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public IdTextPair getPais() {
        return pais;
    }

    public void setPais(IdTextPair pais) {
        this.pais = pais;
    }

    public IdTextPair getProvincia() {
        return provincia;
    }

    public void setProvincia(IdTextPair provincia) {
        this.provincia = provincia;
    }

    public IdTextPair getMunicipio() {
        return municipio;
    }

    public void setMunicipio(IdTextPair municipio) {
        this.municipio = municipio;
    }

    public IdTextPair getLocalidad() {
        return localidad;
    }

    public void setLocalidad(IdTextPair localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
