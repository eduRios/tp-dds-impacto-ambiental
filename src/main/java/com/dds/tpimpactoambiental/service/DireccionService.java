package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.DireccionDto;
import com.dds.tpimpactoambiental.model.Direccion;
import com.dds.tpimpactoambiental.model.Localidad;
import com.dds.tpimpactoambiental.repository.DireccionRepository;
import com.dds.tpimpactoambiental.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class DireccionService {

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Transactional
    public Direccion getOrCreateDireccion(DireccionDto direccionDto) {
        Optional<Direccion> direccionOptional;
        if (direccionDto.getId() != null && direccionDto.getId() != 0) {
            Direccion direccion = direccionRepository.getById(direccionDto.getId());
            direccionOptional = Optional.ofNullable(direccion);
        } else {
            direccionOptional = direccionRepository.getByDireccionEntera(
                    direccionDto.getCalle(),
                    direccionDto.getAltura(),
                    direccionDto.getLocalidad().getId()
            );
        }

        if (direccionOptional.isPresent()) {
            return direccionOptional.get();
        }

        Direccion direccion = new Direccion(direccionDto.getCalle(), direccionDto.getAltura());
        Localidad localidad = localidadRepository.getById(direccionDto.getLocalidad().getId());
        localidad.addDireccion(direccion);
        direccionRepository.save(direccion);
        return direccion;
    }
}
