package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends JpaRepository<Organizacion, Long> {
    Organizacion findByRazonSocial(@Param("razonSocial") String razonSocial);

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM Organizacion")
    boolean hasData();
}
