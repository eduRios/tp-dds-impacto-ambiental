package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.MiembroDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.service.MiembroService;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/liviano/miembro")
public class MiembroControllerLiviano {

    private final MiembroService miembroService;

    private final Handlebars handlebars;

    public MiembroControllerLiviano(MiembroService miembroService) {
        this.miembroService = miembroService;
        this.handlebars = new Handlebars();
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> obtenerVistaDeMiembros() throws IOException {
        Template template = handlebars.compile("/templates/listar_miembros");
        ResponseWithResults<MiembroDto> miembros = miembroService.listarMiembros();

        Map<String, Object> model = new HashMap<>();
        model.put("listaMiembros", miembros.getResults());

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }
}
