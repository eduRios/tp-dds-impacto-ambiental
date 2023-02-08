package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.MiembroDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.request.RequestRegistrarMiembro;
import com.dds.tpimpactoambiental.service.MiembroService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/miembro")
public class MemberController {
    @Autowired
    MiembroService miembroService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> registrarMiembro(@RequestBody RequestRegistrarMiembro miembroDTO){
        return miembroService.registrarMiembro(miembroDTO);
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<MiembroDto>> listarMiembros() {
        return ResponseEntityUtils.toResponseEntity(miembroService.listarMiembros());
    }

    private ResponseEntity<Object> registrarTrayectoria(){
        return null;
    }
}
