package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.UnidadDto;
import com.dds.tpimpactoambiental.service.UnidadService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/unidad")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    @GetMapping
    public ResponseEntity<ResponseWithResults<UnidadDto>> listarUnidades() {
        return ResponseEntityUtils.toResponseEntity(unidadService.listarUnidades());
    }

}
