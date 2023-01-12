package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.TrayectDto;
import com.dds.tpimpactoambiental.dtos.TrayectoDto;
import com.dds.tpimpactoambiental.service.TrayectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trayecto")
public class TrayectoController {

    @Autowired
    private TrayectoService trayectoService;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> crearTrayecto(@RequestBody TrayectoDto trayectoDto) {
        trayectoService.crearTrayecto(trayectoDto);
        return new ResponseEntity<>("yes", HttpStatus.CREATED);
    }
}
