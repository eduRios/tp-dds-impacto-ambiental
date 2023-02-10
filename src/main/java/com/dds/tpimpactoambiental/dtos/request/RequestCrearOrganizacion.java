package com.dds.tpimpactoambiental.dtos.request;

import com.dds.tpimpactoambiental.dtos.BaseEntityDto;
import com.dds.tpimpactoambiental.dtos.CantidadDto;
import com.dds.tpimpactoambiental.dtos.ContactoDto;
import com.dds.tpimpactoambiental.dtos.IdTextPair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestCrearOrganizacion extends BaseEntityDto {

    private String razonSocial;
    private IdTextPair tipoOrganizacion;
    private IdTextPair clasificacion;
    private CantidadDto factorK;
    private int cantDiasHabilesPorSemana;
    private List<IdTextPair> sectores = new ArrayList<>();
    private List<ContactoDto> contactos = new ArrayList<>();
    private List<IdTextPair> solicitudes = new ArrayList<>();

    public RequestCrearOrganizacion() {
    }
/*
    public static OrganizacionDto from(Organizacion organizacion) {
        OrganizacionDto dto = new OrganizacionDto(organizacion);
        dto.setRazonSocial(organizacion.getRazonSocial());
        dto.setTipoOrganizacion(EnumUtils.enumToIdTextPair(organizacion.getTipoOrganizacion()));
        dto.setClasificacion(EnumUtils.enumToIdTextPair(organizacion.getClasificacion()));
        dto.setFactorK(CantidadDto.from(organizacion.getFactorK()));
        dto.setCantDiasHabilesPorSemana(organizacion.getCantDiasHabilesPorSemana());
        dto.setSectores(ListUtils.toIdTextPairList(organizacion.getSectores()));
        dto.setSolicitudes(ListUtils.toIdTextPairList(organizacion.getSolicitudes()));
        dto.setContactos(organizacion.getContactos().stream().map(ContactoDto::from).collect(Collectors.toList()));
        return dto;
    }*/

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public IdTextPair getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(IdTextPair tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public IdTextPair getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(IdTextPair clasificacion) {
        this.clasificacion = clasificacion;
    }

    public CantidadDto getFactorK() {
        return factorK;
    }

    public void setFactorK(CantidadDto factorK) {
        this.factorK = factorK;
    }

    public int getCantDiasHabilesPorSemana() {
        return cantDiasHabilesPorSemana;
    }

    public void setCantDiasHabilesPorSemana(int cantDiasHabilesPorSemana) {
        this.cantDiasHabilesPorSemana = cantDiasHabilesPorSemana;
    }

    public List<IdTextPair> getSectores() {
        return sectores;
    }

    public void setSectores(List<IdTextPair> sectores) {
        this.sectores = sectores;
    }

    public List<ContactoDto> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoDto> contactos) {
        this.contactos = contactos;
    }

    public List<IdTextPair> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<IdTextPair> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
