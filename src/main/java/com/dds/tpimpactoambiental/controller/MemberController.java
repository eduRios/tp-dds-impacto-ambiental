package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarMiembro;
import com.dds.tpimpactoambiental.service.MiembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MiembroService miembroService;

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ResponseEntity<Object> registrarMiembro(@RequestBody RequestRegistrarMiembro miembroDTO){
        return miembroService.registrarMiembro(miembroDTO);
    }

    private ResponseEntity<Object> registrarTrayectoria(){
        return null;
    }
}
