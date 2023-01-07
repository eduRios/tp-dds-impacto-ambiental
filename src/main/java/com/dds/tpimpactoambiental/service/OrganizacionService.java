package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.request.RequestAceptarSolicitud;
import com.dds.tpimpactoambiental.dtos.response.ResponseAceptarSolicitud;
import com.dds.tpimpactoambiental.model.*;
import com.dds.tpimpactoambiental.repository.LocalidadRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import com.dds.tpimpactoambiental.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

    public OrganizacionService() {
    }

    @Transactional
    public ResponseEntity<Object> aceptarSolicitud(RequestAceptarSolicitud request){
        ResponseAceptarSolicitud response = new ResponseAceptarSolicitud();
        Solicitud solicitud = solicitudRepository.findById(request.getIdSolicitud()).orElse(null);
        if (solicitud == null) {
            response.setMessage("No existe ninguna Solicitud con el ID ingresado");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
        solicitud.getSector().aceptarSolicitud(solicitud);
        solicitud.getMiembro().setFechaIngreso(LocalDate.now());
        response.setMessage("Solicitud aceptada");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Transactional
    public void seedData() {

        Organizacion organizacion = new Organizacion("Organizacion TEST", "SA", "Software factory");
        Direccion direccionEspacio = new Direccion("MEDRANO", "951");
        Localidad localidad = localidadRepository.getByNombre("ALMAGRO");
        localidad.addDireccion(direccionEspacio);
        Espacio espacio = new Espacio(direccionEspacio, "Espacio TEST", TipoEspacio.TRABAJO);
            Sector sector = new Sector("Sector TEST", organizacion, espacio);
        organizacion.addSector(sector);
        organizationRepository.saveAll(Arrays.asList(organizacion));
    }

}
