package com.dds.tpimpactoambiental.controller;


import com.dds.tpimpactoambiental.dtos.request.CrearTransportePublicoRequest;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.service.TransportePublicoService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportepublico")
public class TransportePublicoController {

    private final TransportePublicoService transportePublicoService;

    public TransportePublicoController(TransportePublicoService transportePublicoService) {
        this.transportePublicoService = transportePublicoService;
    }

    //@RequireAdminRole
    @PostMapping
    public ResponseEntity<Response> crearTransportePublico(@RequestBody CrearTransportePublicoRequest request) {
        return ResponseEntityUtils.toResponseEntity(transportePublicoService.crearTransportePublico(request));
    }

}
