package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.Cantidad;
import com.dds.tpimpactoambiental.model.SectorTerritorial;

public class SectorTerritorialConHC {
    private IdTextPair sectorTerritorial;
    private CantidadDto hc;

    public static SectorTerritorialConHC from(SectorTerritorial sectorTerritorial, Cantidad hc) {
        SectorTerritorialConHC sectorTerritorialConHC = new SectorTerritorialConHC();
        sectorTerritorialConHC.setSectorTerritorial(new IdTextPair(sectorTerritorial));
        sectorTerritorialConHC.setHc(CantidadDto.from(hc));
        return sectorTerritorialConHC;
    }
    public static SectorTerritorialConHC from(SectorTerritorial sectorTerritorial) {
        SectorTerritorialConHC sectorTerritorialConHC = new SectorTerritorialConHC();
        sectorTerritorialConHC.setSectorTerritorial(new IdTextPair(sectorTerritorial));
        return sectorTerritorialConHC;
    }

    public SectorTerritorialConHC() {
    }

    public IdTextPair getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(IdTextPair sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }

    public CantidadDto getHc() {
        return hc;
    }

    public void setHc(CantidadDto hc) {
        this.hc = hc;
    }
}
