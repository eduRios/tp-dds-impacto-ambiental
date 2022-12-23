package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Espacio;
import com.dds.tpimpactoambiental.model.Lugar;

//@NoArgsConstructor
public class LugarDto extends BaseEntityDto {
    private DireccionDto direccion;
    private String tipo; // "Espacio" o "Parada"

    public LugarDto() {
    }

    private LugarDto(Lugar lugar) {
        super(lugar);
    }

    public static LugarDto from(Lugar lugar) {
        LugarDto dto = new LugarDto(lugar);
        dto.setDireccion(DireccionDto.from(lugar.getDireccion()));
        dto.setTipo(getTipo(lugar));
        return dto;
    }

    private static String getTipo(Lugar lugar) {
        if (lugar instanceof Espacio)
            return "Espacio";
        /*if (lugar instanceof Parada)
            return "Parada";*/
        return "?";
    }

    public DireccionDto getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDto direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
