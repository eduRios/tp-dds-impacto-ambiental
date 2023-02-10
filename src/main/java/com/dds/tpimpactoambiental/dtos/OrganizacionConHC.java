package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Cantidad;
import com.dds.tpimpactoambiental.model.Organizacion;

public class OrganizacionConHC {
    private IdTextPair organizacion;
    private CantidadDto hc;

    public OrganizacionConHC() {
    }

    public static OrganizacionConHC from(Organizacion organizacion, Cantidad hc) {
        OrganizacionConHC organizacionConHC = new OrganizacionConHC();
        organizacionConHC.setOrganizacion(new IdTextPair(organizacion));
        organizacionConHC.setHc(CantidadDto.from(hc));
        return organizacionConHC;
    }

    public IdTextPair getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(IdTextPair organizacion) {
        this.organizacion = organizacion;
    }

    public CantidadDto getHc() {
        return hc;
    }

    public void setHc(CantidadDto hc) {
        this.hc = hc;
    }
}
