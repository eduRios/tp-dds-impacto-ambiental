package com.dds.tpimpactoambiental.controller;

import com.dds.tpimpactoambiental.dtos.request.RequestAceptarSolicitud;
import com.dds.tpimpactoambiental.dtos.request.RequestCrearOrganizacion;
import com.dds.tpimpactoambiental.service.ExcelHelper;
import com.dds.tpimpactoambiental.service.ExcelService;
import com.dds.tpimpactoambiental.service.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizacionService organizacionService;
    @Autowired
    ExcelService fileService;
    @PostMapping(path = "/upload/{idOrg}")
    public ResponseEntity<Object> uploadFile(@PathVariable Long idOrg,@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file,idOrg);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping(path = "/crear-organizacion")
    public ResponseEntity<Object> crearOrganizacion(@RequestBody RequestCrearOrganizacion request){
        return organizacionService.crearOrganizacion(request);
    }
    @PostMapping(path = "/aceptar-solicitud")
    public ResponseEntity<Object> aceptarSolicitud(@RequestBody RequestAceptarSolicitud request){
        return organizacionService.aceptarSolicitud(request);
    }
}
