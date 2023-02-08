package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestCrearSector;
import com.dds.tpimpactoambiental.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sector")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @PostMapping
    public ResponseEntity<Object> crearSector(@RequestBody RequestCrearSector request) {
        return sectorService.crearSector(request);
    }
}
