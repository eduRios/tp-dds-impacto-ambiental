package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.MedioDeTransporteDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.repository.MedioTransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedioDeTransporteService {
    @Autowired
    private MedioTransporteRepository repository;

    @Transactional
    public ResponseWithResults<MedioDeTransporteDto> listarMediosDeTransporte() {
        List<MedioDeTransporteDto> dtos = repository.findAll().stream()
                .map(MedioDeTransporteDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
