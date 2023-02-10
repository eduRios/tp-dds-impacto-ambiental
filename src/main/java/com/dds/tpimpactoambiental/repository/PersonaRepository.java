package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Parada;
import com.dds.tpimpactoambiental.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM Persona")
    boolean hasData();
}
