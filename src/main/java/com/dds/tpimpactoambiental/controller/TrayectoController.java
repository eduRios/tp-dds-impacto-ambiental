package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.TrayectoDto;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.service.TrayectoService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trayecto")
public class TrayectoController {

    @Autowired
    private TrayectoService trayectoService;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Response> crearTrayecto(@RequestBody TrayectoDto trayectoDto) {
        trayectoService.crearTrayecto(trayectoDto);
        return ResponseEntityUtils.toResponseEntity(new Response(HttpStatus.CREATED,"Trayecto creado"));
    }
}
