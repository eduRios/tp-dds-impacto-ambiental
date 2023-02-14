package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.*;

public class MedioDeTransporteDto extends BaseEntityDto {
    private String tipo;
    private String descripcion;

    private MedioDeTransporteDto(MedioTransporte medioDeTransporte) {
        super(medioDeTransporte);
    }

    public static MedioDeTransporteDto from(MedioTransporte medioDeTransporte) {
        MedioDeTransporteDto dto = new MedioDeTransporteDto(medioDeTransporte);
        dto.setTipo(getTipo(medioDeTransporte));
        dto.setDescripcion(medioDeTransporte.toString());
        return dto;
    }

    public MedioDeTransporteDto() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private static String getTipo(MedioTransporte medioDeTransporte) {
        if (medioDeTransporte instanceof TransportePublico)
            return "Transporte publico";
        if (medioDeTransporte instanceof Otro)
            return "Vehiculo no contaminante";
        if (medioDeTransporte instanceof VehiculoParticular)
            return "Vehiculo particular";
        if (medioDeTransporte instanceof ServicioContratado)
            return "Servicio contratado";
        return "?";
    }
}
