package com.dds.tpimpactoambiental.dtos;


import com.dds.tpimpactoambiental.model.Tramo;
import com.dds.tpimpactoambiental.utils.ListUtils;

import java.util.List;

public class TramoDto extends BaseEntityDto {
    private IdTextPair medioDeTransporte;
    private LugarDto lugarInicio;
    private LugarDto lugarFin;
    private List<IdTextPair> miembros;
    //private CantidadDto distanciaRecorrida;


    public TramoDto() {
    }

    private TramoDto(Tramo tramo) {
        super(tramo);
    }

    public static TramoDto from(Tramo tramo) {
        TramoDto dto = new TramoDto(tramo);
        dto.setMedioDeTransporte(new IdTextPair(tramo.getMedioDeTransporte()));
        dto.setLugarInicio(LugarDto.from(tramo.getLugarInicio()));
        dto.setLugarFin(LugarDto.from(tramo.getLugarFin()));
        dto.setMiembros(ListUtils.toIdTextPairList(tramo.getMiembros()));
        //dto.setDistanciaRecorrida(CantidadDto.from(tramo.getDistanciaRecorrida()));
        return dto;
    }

    public IdTextPair getMedioDeTransporte() {
        return medioDeTransporte;
    }

    public void setMedioDeTransporte(IdTextPair medioDeTransporte) {
        this.medioDeTransporte = medioDeTransporte;
    }

    public LugarDto getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(LugarDto lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public LugarDto getLugarFin() {
        return lugarFin;
    }

    public void setLugarFin(LugarDto lugarFin) {
        this.lugarFin = lugarFin;
    }

    public List<IdTextPair> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<IdTextPair> miembros) {
        this.miembros = miembros;
    }
}
