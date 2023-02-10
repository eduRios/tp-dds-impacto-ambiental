package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    Sector getByNombre(@Param("nombre") String nombre);
}
