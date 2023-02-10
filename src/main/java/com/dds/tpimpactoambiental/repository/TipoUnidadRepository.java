package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Sector;
import com.dds.tpimpactoambiental.model.TipoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TipoUnidadRepository extends JpaRepository<TipoUnidad, Long> {
    Optional<TipoUnidad> findByNombre(@Param("nombre") String nombre);
}
