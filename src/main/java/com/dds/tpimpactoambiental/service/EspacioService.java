package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.EspacioDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.repository.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspacioService {

    @Autowired
    private EspacioRepository repository;

    @Transactional
    public ResponseWithResults<EspacioDto> listarEspacios() {
        List<EspacioDto> dtos = repository.findAll().stream()
                .map(EspacioDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
