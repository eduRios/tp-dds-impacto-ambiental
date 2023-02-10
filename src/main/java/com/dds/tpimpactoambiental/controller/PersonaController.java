package com.dds.tpimpactoambiental.controller;


import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.dtos.PersonaDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.dtos.request.RequestCrearPersona;
import com.dds.tpimpactoambiental.dtos.response.Response;
import com.dds.tpimpactoambiental.service.PersonaService;
import com.dds.tpimpactoambiental.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Response> crearPersona(@RequestBody RequestCrearPersona request) {
        return ResponseEntityUtils.toResponseEntity(personaService.crearPersona(request));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<PersonaDto>> listarPersonas() {
        return ResponseEntityUtils.toResponseEntity(personaService.listarPersonas());
    }

    @GetMapping("/idtextpair")
    public ResponseEntity<ResponseWithResults<IdTextPair>> listarPersonasIdTextPair() {
        return ResponseEntityUtils.toResponseEntity(personaService.listarPersonasIdTextPair());
    }

    @GetMapping("/tipos-documento")
    public ResponseEntity<ResponseWithResults<IdTextPair>> listarTiposDeDocumento() {
        return ResponseEntityUtils.toResponseEntity(personaService.listarTiposDeDocumento());
    }
}
