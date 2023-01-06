package com.dds.tpimpactoambiental.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.dds.tpimpactoambiental.model.DatosActividad;
import com.dds.tpimpactoambiental.model.Organizacion;
import com.dds.tpimpactoambiental.repository.DatosActividadRepository;
import com.dds.tpimpactoambiental.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    DatosActividadRepository repository;
    public void save(MultipartFile file, Long idOrg) {
        try {
            Optional<Organizacion> organizacion = organizationRepository.findById(idOrg);
            List<DatosActividad> tutorials = repository.saveAll(ExcelHelper.excelToTutorials(file.getInputStream()));
            Organizacion o = organizacion.get();
            o.setDatosActividadList(tutorials);
            Organizacion oS = organizationRepository.save(o);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<DatosActividad> getAllTutorials() {
        return repository.findAll();
    }
}
