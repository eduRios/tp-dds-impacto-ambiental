package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.ClasificacionConHC;
import com.dds.tpimpactoambiental.dtos.OrganizacionConHC;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.SectorTerritorialConHC;
import com.dds.tpimpactoambiental.enums.Clasificacion;
import com.dds.tpimpactoambiental.model.Cantidad;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.model.SectorTerritorial;
import com.dds.tpimpactoambiental.repository.SectorTerritorialRepository;
import com.dds.tpimpactoambiental.service.CalculadoraHC;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/reportehc")
public class ReporteHCController {

    @Autowired
    private CalculadoraHC calculadoraHC;
    @Autowired
    private SectorTerritorialRepository sectorTerritorialService;


    @GetMapping("/hc-por-sector-territorial")
    public ResponseEntity<ResponseWithResults<SectorTerritorialConHC>> hcPorSectorTerritorial() {
        List<SectorTerritorial> sectoresTerritoriales = sectorTerritorialService.findAll();
        List<SectorTerritorialConHC> sectoresTerritorialesConHCs = sectoresTerritoriales.stream()
                .map(sectorTerritorial -> {
                    Cantidad hc = calculadoraHC.hcTotalSectorTerritorial(sectorTerritorial);
                    return SectorTerritorialConHC.from(sectorTerritorial, hc);
                })
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, sectoresTerritorialesConHCs));
    }

    @GetMapping("/composicion-de-sector-territorial/{idSectorTerritorial}")
    public ResponseEntity<ResponseWithResults<OrganizacionConHC>> composicionDeSectorTerritorial(@PathVariable long idSectorTerritorial) {
        SectorTerritorial sectorTerritorial = sectorTerritorialService.getById(idSectorTerritorial);
        Map<Organizacion, Cantidad> hcsPorOrganizacion = calculadoraHC.hcTotalSectorTerritorialSeparadoPorOrganizacion(sectorTerritorial);
        List<OrganizacionConHC> organizacionesConHCs = hcsPorOrganizacion.entrySet().stream()
                .map(entry -> OrganizacionConHC.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, organizacionesConHCs));
    }

    @GetMapping("/hc-por-clasificacion-organizacion")
    public ResponseEntity<ResponseWithResults<ClasificacionConHC>> hcPorClasificacionOrganizacion() {
        Map<Clasificacion, Cantidad> hcsPorClasificacion = calculadoraHC.hcTotalSeparadoPorClasificacionDeOrganizacion();
        List<ClasificacionConHC> clasificacionesConHCs = hcsPorClasificacion.entrySet().stream()
                .map(entry -> ClasificacionConHC.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, clasificacionesConHCs));
    }

}
