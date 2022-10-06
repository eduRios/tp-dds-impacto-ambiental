package com.dds.tpimpactoambiental.service;
import java.io.IOException;
import java.util.List;

import com.dds.tpimpactoambiental.model.DatosActividad;
import com.dds.tpimpactoambiental.repository.DatosActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {
    @Autowired
    DatosActividadRepository repository;
    public void save(MultipartFile file) {
        try {
            List<DatosActividad> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<DatosActividad> getAllTutorials() {
        return repository.findAll();
    }
}
