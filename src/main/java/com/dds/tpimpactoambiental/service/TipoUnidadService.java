package com.dds.tpimpactoambiental.service;

import com.dds.tpimpactoambiental.model.TipoUnidad;
import com.dds.tpimpactoambiental.repository.TipoUnidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TipoUnidadService {

    private TipoUnidadRepository repository;

    @Transactional
    public Optional<TipoUnidad> getByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Transactional
    public TipoUnidad getLongitud() {
        return getOrThrow("Longitud");
    }

    @Transactional
    public TipoUnidad getMasa() {
        return getOrThrow("Masa");
    }

    @Transactional
    public TipoUnidad getVolumen() {
        return getOrThrow("Volumen");
    }

    @Transactional
    public TipoUnidad getEnergia() {
        return getOrThrow("Energia");
    }

    @Transactional
    public TipoUnidad getEquivalenteCarbono() {
        return getOrThrow("EquivalenteCarbono");
    }

    private TipoUnidad getOrThrow(String nombre) {
        return getByNombre(nombre)
                .orElseThrow(() -> new NoSuchElementException("No se cargo ningun TipoUnidad para " + nombre));
    }
}
