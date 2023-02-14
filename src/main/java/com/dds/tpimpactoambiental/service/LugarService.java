package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.dtos.LugarDto;
import com.dds.tpimpactoambiental.dtos.ResponseWithResults;
import com.dds.tpimpactoambiental.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LugarService {
    @Autowired
    private LugarRepository repository;

    @Transactional
    public ResponseWithResults<LugarDto> listarLugares() {
        List<LugarDto> dtos = repository.getAll().stream()
                .map(LugarDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }
}
