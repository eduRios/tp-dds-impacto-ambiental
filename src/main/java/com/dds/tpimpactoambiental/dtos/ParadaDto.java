package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Parada;

public class ParadaDto extends BaseEntityDto {
    private IdTextPair transportePublico;
    private DireccionDto direccion;
    private CantidadDto distanciaParadaSiguiente;
    private long idParadaSiguiente;

    private ParadaDto(Parada parada) {
        super(parada);
    }

    public static ParadaDto from(Parada parada) {
        ParadaDto dto = new ParadaDto(parada);
        dto.setTransportePublico(new IdTextPair(parada.getTransportePublico()));
        dto.setDireccion(DireccionDto.from(parada.getDireccion()));
        dto.setDistanciaParadaSiguiente(CantidadDto.from(parada.getDistanciaParadaSiguiente()));
        if (parada.getParadaSiguiente() != null) {
            dto.setIdParadaSiguiente(parada.getParadaSiguiente().getId());
        }
        return dto;
    }

    public ParadaDto() {
    }

    public IdTextPair getTransportePublico() {
        return transportePublico;
    }

    public void setTransportePublico(IdTextPair transportePublico) {
        this.transportePublico = transportePublico;
    }

    public DireccionDto getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDto direccion) {
        this.direccion = direccion;
    }

    public CantidadDto getDistanciaParadaSiguiente() {
        return distanciaParadaSiguiente;
    }

    public void setDistanciaParadaSiguiente(CantidadDto distanciaParadaSiguiente) {
        this.distanciaParadaSiguiente = distanciaParadaSiguiente;
    }

    public long getIdParadaSiguiente() {
        return idParadaSiguiente;
    }

    public void setIdParadaSiguiente(long idParadaSiguiente) {
        this.idParadaSiguiente = idParadaSiguiente;
    }
}
