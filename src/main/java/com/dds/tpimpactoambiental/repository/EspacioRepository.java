package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Long> {
}
