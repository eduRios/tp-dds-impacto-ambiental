package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.MedioDeTransporteDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.service.MedioDeTransporteService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medioDeTransporte")
public class MedioDeTransporteController {

    private final MedioDeTransporteService medioDeTransporteService;

    public MedioDeTransporteController(MedioDeTransporteService medioDeTransporteService) {
        this.medioDeTransporteService = medioDeTransporteService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<MedioDeTransporteDto>> listarMediosDeTransporte() {
        return ResponseEntityUtils.toResponseEntity(medioDeTransporteService.listarMediosDeTransporte());
    }
}
