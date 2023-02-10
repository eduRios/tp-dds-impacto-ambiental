package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.enums.Periodicidad;
import com.dds.tpimpactoambiental.model.RegistroCalculoHCDatoActividad;
import com.dds.tpimpactoambiental.repository.RegistroCalculoHCDatoActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegistroCalculoHCDatoActividadService {

    @Autowired
    private RegistroCalculoHCDatoActividadRepository repository;

    public Optional<RegistroCalculoHCDatoActividad> getRegistroCalculoHCParaPeriodo(long idOrganizacion, Periodicidad periodicidad, LocalDate periodo) {
        return repository.getRegistroCalculoHCParaPeriodo(idOrganizacion, periodicidad, periodo);
    }

    public RegistroCalculoHCDatoActividad save(RegistroCalculoHCDatoActividad registroCalculoHCDatoActividad){
        try {
            repository.save(registroCalculoHCDatoActividad);
        } catch (Exception ex) {
            //log.error("Error al llamar a save(), asi que se llama a merge()", ex);
            registroCalculoHCDatoActividad = repository.merge(registroCalculoHCDatoActividad);
        }
        return registroCalculoHCDatoActividad;
    }
}
