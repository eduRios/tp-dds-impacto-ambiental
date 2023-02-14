package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.LugarDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.service.LugarService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lugar")
public class LugarController {

    private final LugarService lugarService;

    public LugarController(LugarService lugarService) {
        this.lugarService = lugarService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<LugarDto>> listarLugares() {
        return ResponseEntityUtils.toResponseEntity(lugarService.listarLugares());
    }
}
