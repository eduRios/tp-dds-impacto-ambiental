package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.enums.Clasificacion;
import com.dds.tpimpactoambiental.model.Cantidad;

public class ClasificacionConHC {
    private IdTextPair clasificacion;
    private CantidadDto hc;

    public ClasificacionConHC() {
    }

    public static ClasificacionConHC from(Clasificacion clasificacion, Cantidad hc) {
        ClasificacionConHC clasificacionConHC = new ClasificacionConHC();
        clasificacionConHC.setClasificacion(new IdTextPair(clasificacion.ordinal(), clasificacion.toString()));
        clasificacionConHC.setHc(CantidadDto.from(hc));
        return clasificacionConHC;
    }

    public IdTextPair getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(IdTextPair clasificacion) {
        this.clasificacion = clasificacion;
    }

    public CantidadDto getHc() {
        return hc;
    }

    public void setHc(CantidadDto hc) {
        this.hc = hc;
    }
}
