package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnidadRepository extends JpaRepository<Unidad,Long> {

    Optional<Unidad> getBySimbolo(@Param("simbolo") String simbolo);

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM Unidad")
    boolean hasData();
}
