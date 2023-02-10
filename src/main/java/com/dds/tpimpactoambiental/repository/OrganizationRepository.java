package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.enums.Clasificacion;
import com.dds.tpimpactoambiental.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organizacion, Long> {
    Organizacion findByRazonSocial(@Param("razonSocial") String razonSocial);

     List<Organizacion> findByClasificacion(@Param("clasificacion") Clasificacion clasificacion);
    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM Organizacion")
    boolean hasData();
}
