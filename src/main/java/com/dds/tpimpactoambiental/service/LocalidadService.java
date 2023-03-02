package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.IdTextPair;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.repository.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalidadService {

    @Autowired
    private LocalidadRepository repository;

    @Transactional
    public ResponseWithResults<IdTextPair> listarLocalidadesIdTextPair() {
        List<IdTextPair> idTextPairs = repository.getAll().stream()
                .map(IdTextPair::new)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, idTextPairs);
    }
}
