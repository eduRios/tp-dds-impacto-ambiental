package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.BaseEntity;
import com.dds.tpimpactoambiental.model.Trayecto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class TrayectoDto extends BaseEntityDto {
    private LugarDto lugarInicio;
    private LugarDto lugarFin;
    private List<MiembroPorTrayectoDto> miembrosPorTrayecto;
    private List<TramoDto> tramos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public TrayectoDto() {
    }

    private TrayectoDto(Trayecto trayecto) {
        super(trayecto);
    }

    public static TrayectoDto from(Trayecto trayecto) {
        TrayectoDto dto = new TrayectoDto(trayecto);
        dto.setLugarInicio(LugarDto.from(trayecto.getInicio()));
        dto.setLugarFin(LugarDto.from(trayecto.getFin()));
        dto.setMiembrosPorTrayecto(trayecto.getMiembrosPorTrayecto().stream()
                .map(MiembroPorTrayectoDto::from).collect(Collectors.toList()));
        dto.setTramos(trayecto.getTramos().stream()
                .map(TramoDto::from).collect(Collectors.toList()));
        dto.setFechaInicio(trayecto.getFechaInicio());
        dto.setFechaFin(trayecto.getFechaFin());
        return dto;
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

    public List<MiembroPorTrayectoDto> getMiembrosPorTrayecto() {
        return miembrosPorTrayecto;
    }

    public void setMiembrosPorTrayecto(List<MiembroPorTrayectoDto> miembrosPorTrayecto) {
        this.miembrosPorTrayecto = miembrosPorTrayecto;
    }

    public List<TramoDto> getTramos() {
        return tramos;
    }

    public void setTramos(List<TramoDto> tramos) {
        this.tramos = tramos;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
