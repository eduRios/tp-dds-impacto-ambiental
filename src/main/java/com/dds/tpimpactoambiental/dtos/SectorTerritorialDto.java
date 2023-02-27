package com.dds.tpimpactoambiental.dtos;

import com.dds.tpimpactoambiental.model.SectorTerritorial;

public class SectorTerritorialDto {
    private IdTextPair sectorTerritorial;

    public static SectorTerritorialConHC from(SectorTerritorial sectorTerritorial) {
        SectorTerritorialConHC sectorTerritorialConHC = new SectorTerritorialConHC();
        sectorTerritorialConHC.setSectorTerritorial(new IdTextPair(sectorTerritorial));
        return sectorTerritorialConHC;
    }
}
