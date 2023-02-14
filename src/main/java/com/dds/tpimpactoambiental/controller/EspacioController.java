package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.EspacioDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.service.EspacioService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/espacio")
public class EspacioController {

    private final EspacioService espacioService;

    public EspacioController(EspacioService espacioService) {
        this.espacioService = espacioService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<EspacioDto>> listarEspacios() {
        return ResponseEntityUtils.toResponseEntity(espacioService.listarEspacios());
    }
}
