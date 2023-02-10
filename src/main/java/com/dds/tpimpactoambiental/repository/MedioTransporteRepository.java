package com.dds.tpimpactoambiental.repository;

import com.dds.tpimpactoambiental.model.MedioTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedioTransporteRepository extends JpaRepository<MedioTransporte, Long> {

    @Query("SELECT (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) AS EXISTE FROM MedioTransporte")
    boolean hasData();
}
