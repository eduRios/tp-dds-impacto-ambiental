package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestCrearSector;
import com.dds.tpimpactoambiental.dtos.response.ResponseCrearSector;
import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.EspacioRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import com.dds.tpimpactoambiental.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SectorService {

    @Autowired
    private OrganizationRepository organizacionRepository;
    @Autowired
    private EspacioRepository espacioRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private SectorRepository sectorRepository;

    public ResponseEntity<Object> crearSector(RequestCrearSector request) {
        ResponseCrearSector response = new ResponseCrearSector();
        Organizacion organizacion = organizacionRepository.findById(request.getIdOrganizacion()).orElse(null);
        if (organizacion == null) {
            response.setMessage("No existe el Espacio con el ID especificado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Espacio espacio;
        if (request.getEspacio().getId() != null && request.getEspacio().getId() != 0) {
            espacio = espacioRepository.getById(request.getEspacio().getId());
            if (espacio == null) {
                response.setMessage("No existe el Espacio con el ID especificado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            Direccion direccion = direccionService.getOrCreateDireccion(request.getEspacio().getDireccion());
            espacio = new Espacio(direccion, request.getEspacio().getNombre(), TipoEspacio.valueOf(request.getEspacio().getTipoEspacio()));
            espacioRepository.save(espacio);
        }

        Sector nuevoSector = new Sector(request.getNombre(), organizacion, espacio);
        sectorRepository.save(nuevoSector);
        response.setMessage("Sector creado!");
        response.setIdSector(nuevoSector.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
