package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.TrayectoDto;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.service.TrayectoService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/trayecto")
public class TrayectoController {

    @Autowired
    private TrayectoService trayectoService;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Response> crearTrayecto(@RequestBody TrayectoDto trayectoDto) {
        trayectoService.crearTrayecto(trayectoDto);
        return ResponseEntityUtils.toResponseEntity(new Response(HttpStatus.CREATED,"Trayecto creado"));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<TrayectoDto>> listarTrayectos() {
        return ResponseEntityUtils.toResponseEntity(trayectoService.listarTrayectos());
    }
}
